package ch.hslu.edu.enapp.webshop.messages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "purchaseMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseMessage {

    @XmlElement
    private String payId;

    @XmlElement
    private int purchaseId;

    @XmlElement
    private String student;

    @XmlElement
    private BigDecimal totalPrice;

    @XmlElement
    private Date date;

    @XmlElement
    private CustomerMessage customer;

    @XmlElementWrapper(name = "lines")
    @XmlElement(name = "line")
    private List<LineMessage> lines = new ArrayList<>();

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerMessage getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerMessage customer) {
        this.customer = customer;
    }

    public List<LineMessage> getLines() {
        return lines;
    }

    public void setLines(List<LineMessage> lines) {
        this.lines = lines;
    }

    public void addLine(LineMessage line) {
        lines.add(line);
    }

}
