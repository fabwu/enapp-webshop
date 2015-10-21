package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.ShoppingCartServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.service.PurchaseService;

@Named
@SessionScoped
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    ShoppingCartServiceLocal shoppingCartService;

    @Inject
    PurchaseService purchaseService;

    private List<PurchaseitemDTO> purchaseitemList;

    public void addToCart(ProductDTO product, String input) {
        try {
            Integer quantity = Integer.valueOf(input);
            if (quantity > 0) {
                shoppingCartService.addProduct(product, quantity);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void order() {
        purchaseService.order(getPurchaseitemList());
        shoppingCartService.clear();
    }

    public List<PurchaseitemDTO> getPurchaseitemList() {
        purchaseitemList = shoppingCartService.getPurchaseitems();
        return purchaseitemList;
    }

}
