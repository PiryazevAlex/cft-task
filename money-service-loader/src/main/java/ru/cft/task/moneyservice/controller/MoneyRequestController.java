package ru.cft.task.moneyservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MoneyRequestController {
    @RequestMapping("/send")
    void process(@RequestParam(value = "xml") String xml);
}
