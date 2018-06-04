package ru.cft.task.moneyservice.service;

/**
 * Сервис-обработчик запроса
 */
public interface MoneyTransferService {
    /**
     * Метод выполянет обработку запроса
     *
     * @param xml текст запроса
     */
    void process(String xml);
}
