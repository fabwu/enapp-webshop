package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the PURCHASEITEM database table.
 *
 */
@Entity
@NamedQueries({

    @NamedQuery(name = "getPurchaseitemByPurchaseBean", query = "SELECT p FROM Purchaseitem p WHERE p.purchase.purchaseid = :purchase_purchaseid"),

    @NamedQuery(name = "getPurchaseitemByUnitprice", query = "SELECT p FROM Purchaseitem p WHERE p.unitprice = :unitprice"),
    @NamedQuery(name = "getPurchaseitemByQuantity", query = "SELECT p FROM Purchaseitem p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "getPurchaseitemByDescription", query = "SELECT p FROM Purchaseitem p WHERE p.itemNo = :itemNo"),
    @NamedQuery(name = "getPurchaseitemByPurchaseitemid", query = "SELECT p FROM Purchaseitem p WHERE p.purchaseitemid = :purchaseitemid"),
    @NamedQuery(name = "getPurchaseitem", query = "SELECT p FROM Purchaseitem p") })
public class Purchaseitem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseitemid;

    private String itemNo;

    private int quantity;

    private BigDecimal unitprice;

    // bi-directional many-to-one association to Purchase
    @ManyToOne
    @JoinColumn(name = "PURCHASE")
    private Purchase purchase;

    public Purchaseitem() {
    }

    public int getPurchaseitemid() {
        return this.purchaseitemid;
    }

    public void setPurchaseitemid(int purchaseitemid) {
        this.purchaseitemid = purchaseitemid;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
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

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}