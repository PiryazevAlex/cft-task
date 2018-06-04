
package ru.cft.task.moneyservice.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for money-transfer-requestsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="money-transfer-requestsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="money-transfer-request" type="{}money-transfer-requestType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "money-transfer-requestsType", propOrder = {
    "moneyTransferRequest"
})
public class MoneyTransferRequestsType {

    @XmlElement(name = "money-transfer-request", required = true)
    protected List<MoneyTransferRequestType> moneyTransferRequest;

    /**
     * Gets the value of the moneyTransferRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moneyTransferRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMoneyTransferRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MoneyTransferRequestType }
     * 
     * 
     */
    public List<MoneyTransferRequestType> getMoneyTransferRequest() {
        if (moneyTransferRequest == null) {
            moneyTransferRequest = new ArrayList<MoneyTransferRequestType>();
        }
        return this.moneyTransferRequest;
    }

}
