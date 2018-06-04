
package ru.cft.task.moneyservice.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.cft.task.moneyservice.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MoneyTransferRequests_QNAME = new QName("", "money-transfer-requests");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.cft.task.moneyservice.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MoneyTransferRequestsType }
     * 
     */
    public MoneyTransferRequestsType createMoneyTransferRequestsType() {
        return new MoneyTransferRequestsType();
    }

    /**
     * Create an instance of {@link CorrespondentType }
     * 
     */
    public CorrespondentType createCorrespondentType() {
        return new CorrespondentType();
    }

    /**
     * Create an instance of {@link MoneyTransferRequestType }
     * 
     */
    public MoneyTransferRequestType createMoneyTransferRequestType() {
        return new MoneyTransferRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoneyTransferRequestsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "money-transfer-requests")
    public JAXBElement<MoneyTransferRequestsType> createMoneyTransferRequests(MoneyTransferRequestsType value) {
        return new JAXBElement<MoneyTransferRequestsType>(_MoneyTransferRequests_QNAME, MoneyTransferRequestsType.class, null, value);
    }

}
