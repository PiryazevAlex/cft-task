package ru.cft.task.moneyservice.service;

import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;

/**
 * Сервис-обработчик
 */
public interface MoneyTransferService {
    /** Метод выполняет обработку строки, содержащей текст XML запроса
     *
     * @param xml
     */
    void process(String xml);

    void saveTransferInfo(MoneyTransferRequestType request);
}
