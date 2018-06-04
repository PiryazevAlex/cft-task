package ru.cft.task.moneyservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);

    @RequestMapping("/send")
    public String process(@RequestParam(value = "xml") String xml) {
        LOGGER.debug(xml);
        return xml;
    }
}
