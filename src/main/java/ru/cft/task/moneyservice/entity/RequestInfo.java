package ru.cft.task.moneyservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Информация о запросе
 */
@Entity
public class RequestInfo {
    // идентификатор запроса
    @Id
    private String id;
    // дата получения запроса
    private Date requestDate;
    // текст запроса
    @Lob
    private byte[] requestText;

    public void setId(String id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public byte[] getRequestText() {
        return requestText;
    }

    public void setRequestText(byte[] requestText) {
        this.requestText = requestText;
    }

    public String getId() {
        return id;
    }
}
