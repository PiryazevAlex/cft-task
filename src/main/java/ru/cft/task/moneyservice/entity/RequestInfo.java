package ru.cft.task.moneyservice.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Информация о запросе
 */
@Entity
public class RequestInfo extends AbstractPersistable<String> {
    // идентификатор запроса
    @Id
    private String id;
    // дата получения запроса
    private Date requestDate;
    // текст запроса
    @Lob
    private String requestText;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }
}
