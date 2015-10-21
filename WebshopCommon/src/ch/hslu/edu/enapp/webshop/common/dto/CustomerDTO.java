package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customerid;

    private String address;

    private String email;

    private String name;

    private String password;

    private String username;

    private List<PurchaseDTO> purchases;

    public int getCustomerid() {
        return this.customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PurchaseDTO> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    public PurchaseDTO addPurchas(PurchaseDTO purchas) {
        getPurchases().add(purchas);
        purchas.setCustomer(this);

        return purchas;
    }

    public PurchaseDTO removePurchas(PurchaseDTO purchas) {
        getPurchases().remove(purchas);
        purchas.setCustomer(null);

        return purchas;
    }

}