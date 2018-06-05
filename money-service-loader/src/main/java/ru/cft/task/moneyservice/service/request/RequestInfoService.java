package ru.cft.task.moneyservice.service.request;

import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.entity.RequestInfo;

public interface RequestInfoService {
    /**
     * Метод выполяние сохранение объекта {@link RequestInfo}
     *
     * @param requestInfo сохраняемый объект
     * @return сохраненный объект
     */
    RequestInfo save(RequestInfo requestInfo);

}
