package ch.hslu.edu.enapp.webshop.messages;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesOrderMessage {

    @XmlElement(name = "correlationId")
    private String correlationId;

    @XmlElement(name = "dynNAVCustomerNo")
    private String dynNAVCustomerNo;

    @XmlElement(name = "dynNAVSalesOrderNo")
    private String dynNAVSalesOrderNo;

    @XmlElement(name = "lastUpdate")
    private Date lastUpdate;

    @XmlElement(name = "postFinancePayId")
    private int paymentId;

    @XmlElement(name = "purchaseDateTime")
    private Date purchaseDateTime;

    @XmlElement(name = "purchaseId")
    private int purchaseId;

    @XmlElement(name = "purchaseItemCount")
    private int itemCount;

    @XmlElement(name = "purchaseTotalCost")
    private long totalAmount;

    @XmlElement(name = "status")
    private String orderStatus;

    @XmlElement(name = "studentName")
    private String studentName;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getDynNAVCustomerNo() {
        return dynNAVCustomerNo;
    }

    public void setDynNAVCustomerNo(String dynNAVCustomerNo) {
        this.dynNAVCustomerNo = dynNAVCustomerNo;
    }

    public String getDynNAVSalesOrderNo() {
        return dynNAVSalesOrderNo;
    }

    public void setDynNAVSalesOrderNo(String dynNAVSalesOrderNo) {
        this.dynNAVSalesOrderNo = dynNAVSalesOrderNo;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}