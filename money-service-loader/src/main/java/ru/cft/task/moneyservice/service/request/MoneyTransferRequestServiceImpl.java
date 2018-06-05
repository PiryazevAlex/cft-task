package ru.cft.task.moneyservice.service.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.RequestInfoDto;
import ru.cft.task.moneyservice.entity.RequestInfo;
import ru.cft.task.moneyservice.exception.FileCreationException;
import ru.cft.task.moneyservice.service.converter.XmlConverter;
import ru.cft.task.moneyservice.service.file.FileExportService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class MoneyTransferRequestServiceImpl implements MoneyTransferRequestService {
    private final RequestInfoService requestInfoService;
    private final XmlConverter xmlConverter;
    private final FileExportService fileExportService;

    @Autowired
    public MoneyTransferRequestServiceImpl(RequestInfoService requestInfoService,
                                           XmlConverter xmlConverter,
                                           FileExportService fileExportService) {
        this.requestInfoService = requestInfoService;
        this.xmlConverter = xmlConverter;
        this.fileExportService = fileExportService;
    }

    @Override
    @Transactional
    public void process(MoneyTransferRequestType request) {
        try {
            RequestInfo requestInfo = buildRequestInfo(request);
            requestInfoService.save(requestInfo);
            fileExportService.exportData(convertRequest(request));
        } catch (IOException e) {
            throw new FileCreationException(e);
        }

    }

    private RequestInfo buildRequestInfo(MoneyTransferRequestType request) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setId(request.getId());
        requestInfo.setRequestDate(new Date());
        requestInfo.setRequestText(xmlConverter.convert(request).getBytes());
        return requestInfo;
    }

    private RequestInfoDto convertRequest(MoneyTransferRequestType request) {
        RequestInfoDto result = new RequestInfoDto();

        result.setId(request.getId());
        result.setPayerAccount(request.getSender().getAccount());
        result.setPayeeAccount(request.getRecipient().getAccount());
        result.setSum(calculateTransferSum(request));

        return result;
    }

    private static BigDecimal calculateTransferSum(MoneyTransferRequestType request) {
        if (request.getCommission() != null) {
            // расчет как в ТЗ, если комиссия превысит сумму, то отрицательный перевод?
            return request.getAmount().subtract(request.getCommission());
        } else {
            return request.getAmount();
        }
    }
}
