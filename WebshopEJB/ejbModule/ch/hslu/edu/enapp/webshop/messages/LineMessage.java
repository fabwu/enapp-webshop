package ch.hslu.edu.enapp.webshop.messages;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "line")
@XmlAccessorType(XmlAccessType.FIELD)
public class LineMessage {

    @XmlElement
    private String msDynNAVItemNo;

    @XmlElement
    private String description;

    @XmlElement
    private int quantity;

    @XmlElement
    private BigDecimal totalLinePrice;

    public String getMsDynNAVItemNo() {
        return msDynNAVItemNo;
    }

    public void setMsDynNAVItemNo(String msDynNAVItemNo) {
        this.msDynNAVItemNo = msDynNAVItemNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalLinePrice() {
        return totalLinePrice;
    }

    public void setTotalLinePrice(BigDecimal totalLinePrice) {
        this.totalLinePrice = totalLinePrice;
    }

}
