package ch.hslu.edu.enapp.webshop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import ch.hslu.edu.enapp.webshop.common.ShoppingCartServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

/**
 * Session Bean implementation class ShoppingCartService
 */
@Stateful
@LocalBean
public class ShoppingCartService implements ShoppingCartServiceLocal {

    private Map<ProductDTO, PurchaseitemDTO> purchaseitems = new HashMap<>();

    @Override
    public void addProduct(ProductDTO product, int quantity) {
        PurchaseitemDTO purchaseitemDTO;

        if (purchaseitems.containsKey(product)) {
            purchaseitemDTO = purchaseitems.get(product);
        } else {
            purchaseitemDTO = new PurchaseitemDTO();
            purchaseitemDTO.setProduct(product);
        }

        purchaseitemDTO.setQuantity(purchaseitemDTO.getQuantity() + quantity);

        purchaseitemDTO.setTotalprice(product.getUnitprice()
                .multiply(BigDecimal.valueOf(purchaseitemDTO.getQuantity())));

        purchaseitems.put(product, purchaseitemDTO);
    }

    @Override
    public List<PurchaseitemDTO> getPurchaseitems() {
        return new ArrayList<PurchaseitemDTO>(purchaseitems.values());
    }

    @Override
    public void clear() {
        purchaseitems.clear();
    }

}
