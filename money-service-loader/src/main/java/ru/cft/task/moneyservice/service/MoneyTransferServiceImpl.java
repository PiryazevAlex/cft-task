package ru.cft.task.moneyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.runnable.MoneyTransferTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private final ExecutorService executorService;
    private final MoneyTransferRequestService moneyTransferRequestService;
    private final XmlConverter xmlConverter;

    @Autowired
    public MoneyTransferServiceImpl(
            @Value("${worker.poolSize}") int poolSize,
            MoneyTransferRequestService moneyTransferRequestService,
            XmlConverter xmlConverter) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.moneyTransferRequestService = moneyTransferRequestService;
        this.xmlConverter = xmlConverter;
    }

    @Override
    public void process(String xml) {
        MoneyTransferRequestsType requests = xmlConverter.convert(xml);
        requests.getMoneyTransferRequest()
                .forEach(
                        request -> executorService.submit(new MoneyTransferTask(moneyTransferRequestService, request)));

    }

}
