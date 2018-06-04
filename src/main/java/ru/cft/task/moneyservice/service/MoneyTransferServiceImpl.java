package ru.cft.task.moneyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.entity.RequestInfo;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private final XmlConverter xmlLoader;
    private final RequestInfoService requestInfoService;
    private ExecutorService executorService;

    @Autowired
    public MoneyTransferServiceImpl(XmlConverter xmlConverter,
                                    RequestInfoService requestInfoService, @Value("${poolSize}") int poolSize) {
        this.xmlLoader = xmlConverter;
        this.requestInfoService = requestInfoService;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void process(String xml) {
        MoneyTransferRequestsType requests = xmlLoader.convert(xml);

    }

    @Override
    @Transactional
    public void saveTransferInfo(MoneyTransferRequestType request) {
        requestInfoService.save(buildRequestInfo(request));


        // requestInfoService.save()
    }

    private RequestInfo buildRequestInfo(MoneyTransferRequestType request) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setId(request.getId());
        requestInfo.setRequestDate(new Date());
        requestInfo.setRequestText(xmlLoader.convert(request));
        return requestInfo;
    }


}
