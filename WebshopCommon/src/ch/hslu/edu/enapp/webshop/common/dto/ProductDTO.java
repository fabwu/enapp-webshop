package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productid;

    private String description;

    private String mediapath;

    private String name;

    private BigDecimal unitprice;

    private List<PurchaseitemDTO> purchaseitems;

    public int getProductid() {
        return this.productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediapath() {
        return this.mediapath;
    }

    public void setMediapath(String mediapath) {
        this.mediapath = mediapath;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public List<PurchaseitemDTO> getPurchaseitems() {
        return this.purchaseitems;
    }

    public void setPurchaseitems(List<PurchaseitemDTO> purchaseitems) {
        this.purchaseitems = purchaseitems;
    }

    public PurchaseitemDTO addPurchaseitem(PurchaseitemDTO purchaseitem) {
        getPurchaseitems().add(purchaseitem);
        purchaseitem.setProductBean(this);

        return purchaseitem;
    }

    public PurchaseitemDTO removePurchaseitem(PurchaseitemDTO purchaseitem) {
        getPurchaseitems().remove(purchaseitem);
        purchaseitem.setProductBean(null);

        return purchaseitem;
    }

}