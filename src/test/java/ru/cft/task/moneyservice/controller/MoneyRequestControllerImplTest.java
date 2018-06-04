package ru.cft.task.moneyservice.controller;

import org.junit.Test;
import org.mockito.Mock;
import ru.cft.task.moneyservice.exception.InvalidXmlException;
import ru.cft.task.moneyservice.service.MoneyTransferService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

public class MoneyRequestControllerImplTest {
    @Mock
    private MoneyTransferService moneyTransferService;
    private MoneyRequestController moneyRequestController = new MoneyRequestControllerImpl(moneyTransferService);

    @Test
    public void processValidXmlShouldBeHttpOk() {
        doThrow(InvalidXmlException.class).when(moneyTransferService).process(anyString());
        moneyRequestController.process("");
    }
}