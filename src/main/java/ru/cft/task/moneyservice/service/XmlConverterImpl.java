package ru.cft.task.moneyservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.exception.InvalidXmlException;
import ru.cft.task.moneyservice.exception.XmlCreatingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XmlConverterImpl implements XmlConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlConverterImpl.class);
    private static final String MONEY_TRANSFER_REQUEST_TAG = "money-transfer-request";

    @Override
    public MoneyTransferRequestsType convert(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(MoneyTransferRequestsType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<MoneyTransferRequestsType> requests = unmarshaller.unmarshal(
                    new StreamSource(new StringReader(xml)),
                    MoneyTransferRequestsType.class);
            return requests.getValue();
        } catch (JAXBException e) {
            LOGGER.error("Invalid XML source", e);
            throw new InvalidXmlException();
        }
    }

    @Override
    public String convert(MoneyTransferRequestType moneyTransferRequestType) {
        try {
            JAXBContext context = JAXBContext.newInstance(MoneyTransferRequestType.class);
            Marshaller marshaller = context.createMarshaller();
            QName qName = new QName(MONEY_TRANSFER_REQUEST_TAG);
            JAXBElement<MoneyTransferRequestType> root = new JAXBElement<>(qName, MoneyTransferRequestType.class,
                    moneyTransferRequestType);
            StringWriter writer = new StringWriter();
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new XmlCreatingException(e);
        }
    }
}
