package ru.cft.task.moneyservice.service.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.entity.RequestInfo;
import ru.cft.task.moneyservice.exception.AlreadyExistsRequestException;
import ru.cft.task.moneyservice.repository.RequestInfoRepository;

@Service
public class RequestInfoServiceImpl implements RequestInfoService {
    private final RequestInfoRepository requestInfoRepository;

    @Autowired
    public RequestInfoServiceImpl(RequestInfoRepository requestInfoRepository) {
        this.requestInfoRepository = requestInfoRepository;
    }

    @Override
    public RequestInfo save(RequestInfo requestInfo) {
        if (!isRequestExist(requestInfo.getId())) {
            return requestInfoRepository.save(requestInfo);
        } else {
            throw new AlreadyExistsRequestException(requestInfo.getId());
        }
    }

    /**
     * Метод выполняет проверку существования запроса
     *
     * @param requestId идентификатор проверяемого запроса
     * @return true - запрос существует, false - в противном случае
     */
    private boolean isRequestExist(String requestId) {
        return requestInfoRepository.findOneById(requestId)
                .isPresent();
    }

}
