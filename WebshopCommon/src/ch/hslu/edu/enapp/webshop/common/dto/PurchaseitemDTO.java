package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseitemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int purchaseitemid;

    // TODO Remove description
    private String description;

    private int quantity;

    private BigDecimal totalPrice;

    private ProductDTO product;

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

    public BigDecimal getTotalprice() {
        return this.totalPrice;
    }

    public void setTotalprice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductDTO getProduct() {
        return this.product;
    }

    public void setProduct(ProductDTO productBean) {
        this.product = productBean;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        PurchaseitemDTO other = (PurchaseitemDTO) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

}