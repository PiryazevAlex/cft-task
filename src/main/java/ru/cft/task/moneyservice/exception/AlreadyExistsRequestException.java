package ru.cft.task.moneyservice.exception;

import java.text.MessageFormat;

/**
 * Исключение возникает при попытке сохранить запрос с существующим идентификатором
 */
public class AlreadyExistsRequestException extends RuntimeException {
    private static final long serialVersionUID = 5272974815193296393L;
    private static final String MESSAGE = "Запрос с id={0} уже сущетсвует";

    public AlreadyExistsRequestException(String id) {
        super(MessageFormat.format(MESSAGE, id));
    }
}
