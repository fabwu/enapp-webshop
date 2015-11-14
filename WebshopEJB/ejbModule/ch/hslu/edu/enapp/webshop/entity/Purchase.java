package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the PURCHASE database table.
 *
 */
@Entity
@NamedQueries({

    @NamedQuery(name = "getPurchaseByCustomerBean", query = "SELECT p FROM Purchase p WHERE p.customer.customerid = :customer_customerid"),

    @NamedQuery(name = "getPurchaseByState", query = "SELECT p FROM Purchase p WHERE p.state = :state"),
    @NamedQuery(name = "getPurchaseByDatetime", query = "SELECT p FROM Purchase p WHERE p.datetime = :datetime"),
    @NamedQuery(name = "getPurchaseByPurchaseid", query = "SELECT p FROM Purchase p WHERE p.purchaseid = :purchaseid"),
    @NamedQuery(name = "getPurchase", query = "SELECT p FROM Purchase p ORDER BY p.datetime DESC") })
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseid;

    private String messageid;

    private Timestamp datetime;

    @Column(name = "\"STATE\"")
    private String state;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER")
    private Customer customer;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private List<Purchaseitem> purchaseitems;

    public int getPurchaseid() {
        return this.purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
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

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Purchaseitem> getPurchaseitems() {
        return this.purchaseitems;
    }

    public void setPurchaseitems(List<Purchaseitem> purchaseitems) {
        this.purchaseitems = purchaseitems;
    }

    public Purchaseitem addPurchaseitem(Purchaseitem purchaseitem) {
        getPurchaseitems().add(purchaseitem);
        purchaseitem.setPurchase(this);

        return purchaseitem;
    }

    public Purchaseitem removePurchaseitem(Purchaseitem purchaseitem) {
        getPurchaseitems().remove(purchaseitem);
        purchaseitem.setPurchase(null);

        return purchaseitem;
    }

}