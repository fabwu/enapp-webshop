package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the PRODUCT database table.
 *
 */
@Entity
@NamedQueries({

    @NamedQuery(name = "getProductByUnitprice", query = "SELECT p FROM Product p WHERE p.unitprice = :unitprice"),

    @NamedQuery(name = "getProductByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "getProductByMediapath", query = "SELECT p FROM Product p WHERE p.mediapath = :mediapath"),
    @NamedQuery(name = "getProductByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "getProductByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid"),
    @NamedQuery(name = "getProduct", query = "SELECT p FROM Product p") })
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;

    private String description;

    private String mediapath;

    @Column(name = "\"NAME\"")
    private String name;

    private BigDecimal unitprice;

    public Product() {
    }

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

}