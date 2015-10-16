package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseitemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int purchaseitemid;

    private String description;

    private int quantity;

    private BigDecimal unitprice;

    private ProductDTO productBean;

    private PurchaseDTO purchaseBean;

    public int getPurchaseitemid() {
        return this.purchaseitemid;
    }

    public void setPurchaseitemid(int purchaseitemid) {
        this.purchaseitemid = purchaseitemid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public ProductDTO getProductBean() {
        return this.productBean;
    }

    public void setProductBean(ProductDTO productBean) {
        this.productBean = productBean;
    }

    public PurchaseDTO getPurchaseBean() {
        return this.purchaseBean;
    }

    public void setPurchaseBean(PurchaseDTO purchaseBean) {
        this.purchaseBean = purchaseBean;
    }

}