package ru.cft.task.moneyservice.service.converter;

import org.junit.Assert;
import org.junit.Test;
import ru.cft.task.moneyservice.dto.CorrespondentType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.exception.InvalidXmlException;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class XmlConverterImplTest {
    private XmlConverter converter = new XmlConverterImpl();

    @Test
    public void loadedDataShouldBeAsExpected() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<money-transfer-requests>" +
                "  <money-transfer-request id=\"str1234\">" +
                "    <sender name=\"Иванов\" account=\"Счет Иванова\" />" +
                "    <recipient name=\"Петров\" account=\"Счет Петрова\" />" +
                "    <amount>123.45</amount>" +
                "    <commission>123.45</commission>" +
                "  </money-transfer-request>" +
                "</money-transfer-requests>";
        MoneyTransferRequestsType result = converter.convert(xml);
        Assert.assertThat(result.getMoneyTransferRequest(), hasSize(1));
        Assert.assertThat(result.getMoneyTransferRequest().get(0).getAmount(), is(new BigDecimal("123.45")));
        Assert.assertThat(result.getMoneyTransferRequest().get(0).getSender().getName(), is("Иванов"));
    }

    @Test(expected = InvalidXmlException.class)
    public void loadIncorrectXmlShouldThrowException() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "  <person id=\"2\">" +
                "    <name>Test</name>" +
                "    <surname>Test</surname>" +
                "  </peron>";
        converter.convert(xml);
    }

    @Test
    public void convertFromObjectShouldBeAsExpected() {
        MoneyTransferRequestType request = new MoneyTransferRequestType();
        request.setId("id");
        request.setAmount(new BigDecimal("123.45"));
        request.setCommission(new BigDecimal("12.34"));
        request.setRecipient(buildCorrespondent("12345", "recipient"));
        request.setSender(buildCorrespondent("54321", "sender"));
        Assert.assertThat(converter.convert(request), is(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                        "<money-transfer-request id=\"id\">" +
                        "<sender name=\"sender\" account=\"54321\"/>" +
                        "<recipient name=\"recipient\" account=\"12345\"/>" +
                        "<amount>123.45</amount>" +
                        "<commission>12.34</commission>" +
                        "</money-transfer-request>"));
    }

    private CorrespondentType buildCorrespondent(String account, String name) {
        CorrespondentType correspondent = new CorrespondentType();
        correspondent.setAccount(account);
        correspondent.setName(name);
        return correspondent;
    }


}