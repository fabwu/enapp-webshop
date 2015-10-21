package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productid;

    private String description;

    private String mediapath;

    private String name;

    private BigDecimal unitprice;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + productid;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductDTO other = (ProductDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (productid != other.productid)
            return false;
        return true;
    }

}