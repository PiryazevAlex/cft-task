package ru.cft.task.moneyservice;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoneyServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "test-application.properties")
public class MoneyServiceApplicationTests {
    @LocalServerPort
    int port;
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    @Before
    public void setUp() throws Exception {
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        restTemplate = new TestRestTemplate();

        ((HttpComponentsClientHttpRequestFactory) restTemplate.getRestTemplate().getRequestFactory()).setHttpClient(httpClient);
        headers = new HttpHeaders();
    }

    @Test
    public void requestShouldReturnBadRequestOnInvalidXml() {

        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);
        String invalidXml = ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<person id=\"8\">"
                + "  <sender name=\"Иванов\" account=\"Счет Иванова\"/>"
                + "  <recipient name=\"str1234\" account=\"Счет Петрова\"/>"
                + "  <amount>123.46</amount>"
                + "  <commission>123.45</commission>"
                + "</person>";
        ResponseEntity<Void> response = restTemplate.exchange(
                buildSendXmlUrl(invalidXml),
                HttpMethod.GET,
                httpEntity,
                Void.class);
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void requestShouldReturnOkOnValidXml() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);
        String validXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<money-transfer-requests>"
                + "  <money-transfer-request id=\"str1234\">"
                + "    <sender name=\"str1234\" account=\"str1234\" />"
                + "    <recipient name=\"str1234\" account=\"str1234\" />"
                + "    <amount>123.45</amount>"
                + "    <commission>123.45</commission>"
                + "  </money-transfer-request>"
                + "</money-transfer-requests>";
        ResponseEntity<Void> response = restTemplate.exchange(
                buildSendXmlUrl(validXml),
                HttpMethod.GET,
                httpEntity,
                Void.class);
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }


    private String buildSendXmlUrl(String xml) {
        return "https://localhost:" + port + "/send?xml=" + xml;
    }

}
