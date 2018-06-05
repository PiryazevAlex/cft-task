package ru.cft.task.moneyservice.service.converter;

import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;

/**
 * Сервис, производящий работу с XML
 */
public interface XmlConverter {
    /**
     * Метод считывает объекты запросов из XML
     *
     * @param xml строка, содержащая XML
     * @return сформированные объекты
     */
    MoneyTransferRequestsType convert(String xml);

    /**
     * Метод возвращает объект в виде XML
     *
     * @param moneyTransferRequestType преобразуемый объект
     * @return объект в виде XML
     */
    String convert(MoneyTransferRequestType moneyTransferRequestType);
}
