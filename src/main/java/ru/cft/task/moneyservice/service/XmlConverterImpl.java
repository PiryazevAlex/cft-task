package ru.cft.task.moneyservice.service;

import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.exception.InvalidXmlException;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XmlConverterImpl implements XmlConverter {

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
            throw new InvalidXmlException();
        }
    }

    @Override
    public String convert(MoneyTransferRequestType moneyTransferRequestType) {
        try {
            JAXBContext context = JAXBContext.newInstance(MoneyTransferRequestType.class);
            Marshaller marshaller = context.createMarshaller();

            QName qName = new QName("money-transfer-request");
            JAXBElement<MoneyTransferRequestType> root = new JAXBElement<>(qName, MoneyTransferRequestType.class,
                    moneyTransferRequestType);
            StringWriter writer = new StringWriter();
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            //todo другую ошибку выкинуть
            throw new RuntimeException("Marshaling error");
        }
    }
}
