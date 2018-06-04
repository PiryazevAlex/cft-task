package ru.cft.task.moneyservice.runnable;

import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;

/**
 * Задача обработки одного запроса
 */
public class MoneyTransferTask implements Runnable {
    private MoneyTransferRequestType requestType;

    public MoneyTransferTask(MoneyTransferRequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public void run() {

    }
}
