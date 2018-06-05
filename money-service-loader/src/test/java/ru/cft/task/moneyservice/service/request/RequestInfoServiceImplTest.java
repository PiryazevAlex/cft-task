package ru.cft.task.moneyservice.service.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ru.cft.task.moneyservice.entity.RequestInfo;
import ru.cft.task.moneyservice.exception.AlreadyExistsRequestException;
import ru.cft.task.moneyservice.repository.RequestInfoRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RequestInfoServiceImplTest {
    private RequestInfoRepository repository = Mockito.mock(RequestInfoRepository.class);
    private RequestInfoService requestInfoService = new RequestInfoServiceImpl(repository);
    private RequestInfo info;

    @Before
    public void setUp() throws Exception {
        info = new RequestInfo();
        info.setId("1");
        when(repository.save(info))
                .thenAnswer((Answer<RequestInfo>) invocationOnMock -> invocationOnMock.getArgument(0));

    }

    @Test
    public void saveShouldBeSuccess() {
        when(repository.findOneById(anyString())).thenReturn(Optional.empty());
        Assert.assertThat(requestInfoService.save(info).getId(), is("1"));
    }

    @Test(expected = AlreadyExistsRequestException.class)
    public void saveRequestWithExistingIdShouldThrowException() {
        when(repository.findOneById(anyString())).thenReturn(Optional.of(new RequestInfo()));
        requestInfoService.save(info);
    }
}