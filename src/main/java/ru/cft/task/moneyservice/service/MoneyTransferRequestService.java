package ru.cft.task.moneyservice.service;

import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;

/**
 * Сервис для работы с запросами
 */
public interface MoneyTransferRequestService {
    /**
     * Метод выполняет обработку запроса
     *
     * @param request обрабатываемый запрос
     */
    void process(MoneyTransferRequestType request);
}
