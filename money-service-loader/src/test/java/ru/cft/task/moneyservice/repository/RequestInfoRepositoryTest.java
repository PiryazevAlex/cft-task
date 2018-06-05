package ru.cft.task.moneyservice.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.cft.task.moneyservice.entity.RequestInfo;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RequestInfoRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RequestInfoRepository requestInfoRepository;

    @Before
    public void setUp() throws Exception {
        RequestInfo info = new RequestInfo();
        info.setId("id");
        info.setRequestDate(new Date(10000L));
        info.setRequestText("example".getBytes(Charset.defaultCharset()));
        requestInfoRepository.save(info);
    }

    @Test
    public void findOneByIdShouldFindNothing() {
        Assert.assertFalse(requestInfoRepository.findOneById("2").isPresent());
    }
    @Test
    public void findOneByIdShouldFindEntity() {
        Optional<RequestInfo> info = requestInfoRepository.findOneById("id");
        Assert.assertTrue(info.isPresent());
        RequestInfo requestInfo = info.get();
        Assert.assertEquals(requestInfo.getId(), "id");
        Assert.assertEquals(requestInfo.getRequestDate(), new Date(10000L));
        Assert.assertTrue(Arrays.equals(requestInfo.getRequestText(), "example".getBytes(Charset.defaultCharset())));
    }
}