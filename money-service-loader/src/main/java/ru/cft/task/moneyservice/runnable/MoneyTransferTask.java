package ru.cft.task.moneyservice.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.service.request.MoneyTransferRequestService;

/**
 * Задача обработки одного запроса
 */
public class MoneyTransferTask implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferTask.class);
    private final MoneyTransferRequestService moneyTransferRequestService;
    private final MoneyTransferRequestType request;

    public MoneyTransferTask(MoneyTransferRequestService moneyTransferRequestService,
                             MoneyTransferRequestType request) {
        this.moneyTransferRequestService = moneyTransferRequestService;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            moneyTransferRequestService.process(request);
        } catch (Exception e) {
            LOGGER.error("Request processing error", e);
        }
    }
}
