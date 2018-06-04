package ru.cft.task.moneyservice.exception;

/**
 * Исключение возникающее во время преобразования объекта в строку
 */
public class XmlCreatingException extends RuntimeException {
    private static final long serialVersionUID = 6913872804602544116L;
    private static final String MESSAGE = "Ошибка преобразования";

    public XmlCreatingException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
