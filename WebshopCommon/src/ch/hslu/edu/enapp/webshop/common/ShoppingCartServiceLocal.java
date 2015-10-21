package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

@Local
public interface ShoppingCartServiceLocal {

    void addProduct(ProductDTO product, int quantity);

    List<PurchaseitemDTO> getPurchaseitems();

    void clear();

}
