package ru.cft.task.moneyservice.exception;

/**
 * Ошибка возникающая при инициализации контекста
 */
public class JaxbContextInitializationException extends RuntimeException {
    private static final long serialVersionUID = 4316567636839373905L;
    private static final String MESSAGE = "Ошибка инициализации JAXB контекста";

    public JaxbContextInitializationException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
