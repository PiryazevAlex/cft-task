package ru.cft.task.moneyservice.exception;

import java.io.IOException;

/**
 * Исключение, возникающее при создании файла
 */
public class FileCreationException extends RuntimeException {
    private static final long serialVersionUID = -4436791612178664739L;
    private static final String MESSAGE = "Ошибка при созании файла";

    public FileCreationException(IOException e) {
        super(MESSAGE, e);
    }
}
