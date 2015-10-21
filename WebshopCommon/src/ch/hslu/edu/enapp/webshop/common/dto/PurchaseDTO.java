package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class PurchaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int purchaseid;

    private Timestamp datetime;

    private String state;

    private CustomerDTO customerBean;

    private List<PurchaseitemDTO> purchaseitems;

    public int getPurchaseid() {
        return this.purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public Timestamp getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CustomerDTO getCustomerBean() {
        return this.customerBean;
    }

    public void setCustomerBean(CustomerDTO customerBean) {
        this.customerBean = customerBean;
    }

    public List<PurchaseitemDTO> getPurchaseitems() {
        return this.purchaseitems;
    }

    public void setPurchaseitems(List<PurchaseitemDTO> purchaseitems) {
        this.purchaseitems = purchaseitems;
    }

    public PurchaseitemDTO addPurchaseitem(PurchaseitemDTO purchaseitem) {
        getPurchaseitems().add(purchaseitem);

        return purchaseitem;
    }

    public PurchaseitemDTO removePurchaseitem(PurchaseitemDTO purchaseitem) {
        getPurchaseitems().remove(purchaseitem);

        return purchaseitem;
    }

}