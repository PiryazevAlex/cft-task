package ru.cft.task.moneyservice.exception;

/**
 * Исключение возникает при создании схемы
 */
public class SchemaCreationException extends RuntimeException {
    private static final long serialVersionUID = 6055732690350574L;
    private static final String MESSAGE = "Ошибка инициализации XML схемы";

    public SchemaCreationException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
