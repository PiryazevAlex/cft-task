package ru.cft.task.moneyservice.service;

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
