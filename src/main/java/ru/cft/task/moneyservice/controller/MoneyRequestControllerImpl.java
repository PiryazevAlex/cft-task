package ru.cft.task.moneyservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.task.moneyservice.service.MoneyTransferService;

/**
 * Контроллер для обработки запросов
 */
@RestController
public class MoneyRequestControllerImpl implements MoneyRequestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyRequestControllerImpl.class);
    private final MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyRequestControllerImpl(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @Override
    @RequestMapping("/send")
    public void process(@RequestParam(value = "xml") String xml) {
        LOGGER.debug(xml);
        moneyTransferService.process(xml);
    }
}
