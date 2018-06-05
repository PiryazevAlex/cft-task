package ru.cft.task.moneyservice.service.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestType;
import ru.cft.task.moneyservice.dto.MoneyTransferRequestsType;
import ru.cft.task.moneyservice.exception.InvalidXmlException;
import ru.cft.task.moneyservice.exception.JaxbContextInitializationException;
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
    private static final QName Q_NAME = new QName("money-transfer-request");
    private static JAXBContext context;

    static {
        try {
            context = JAXBContext.newInstance(
                    MoneyTransferRequestsType.class,
                    MoneyTransferRequestType.class);
        } catch (JAXBException e) {
            throw new JaxbContextInitializationException(e);
        }
    }

    @Override
    public MoneyTransferRequestsType convert(String xml) {
        try {
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
            Marshaller marshaller = context.createMarshaller();

            JAXBElement<MoneyTransferRequestType> root = new JAXBElement<>(Q_NAME, MoneyTransferRequestType.class,
                    moneyTransferRequestType);
            StringWriter writer = new StringWriter();
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new XmlCreatingException(e);
        }
    }
}
