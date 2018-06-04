package ru.cft.task.moneyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение возникает в случае некорректного xml
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "XML is not valid")
public class InvalidXmlException extends RuntimeException {

}
