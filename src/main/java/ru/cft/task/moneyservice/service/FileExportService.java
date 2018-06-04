package ru.cft.task.moneyservice.service;

import ru.cft.task.moneyservice.dto.RequestInfoDto;

import java.io.IOException;

/**
 * Сервис для работы с файлами
 */
public interface FileExportService {
    /**
     * Метод выполяние сохранение в файл объекта
     *
     * @param dto сохраняемый объект
     */
    void exportData(RequestInfoDto dto) throws IOException;
}
