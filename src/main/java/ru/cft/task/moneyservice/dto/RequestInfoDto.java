package ru.cft.task.moneyservice.dto;

import java.math.BigDecimal;

/**
 * DTO для {@link ru.cft.task.moneyservice.entity.RequestInfo}
 */
public class RequestInfoDto {
    /**
     * id запроса
     */
    private String id;
    /**
     * Счет отправителя
     */
    private String payerAccount;
    /**
     * Счет получателя
     */
    private String payeeAccount;
    /**
     * Сумма перевода, за вычетом комиссии
     */
    private BigDecimal sum;

    public void setId(String id) {
        this.id = id;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
