package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseServiceLocal;
import ch.hslu.edu.enapp.webshop.common.ShoppingCartServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

@Named
@SessionScoped
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    ShoppingCartServiceLocal shoppingCartService;

    @Inject
    CustomerServiceLocal customerService;

    @Inject
    PurchaseServiceLocal purchaseService;

    private List<PurchaseitemDTO> purchaseitemList;

    public void addToCart(ProductDTO product, String input) {
        try {
            Integer quantity = Integer.valueOf(input);
            if (quantity > 0) {
                shoppingCartService.addProduct(product, quantity);
                showInfoMessage("Artikel zum Warenkorb hinzugefügt!");

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void order() {
        List<PurchaseitemDTO> purchaseItems = getPurchaseitemList();
        if (!purchaseItems.isEmpty()) {
            purchaseService.order(purchaseItems, customerService.getCurrentCustomer());
            shoppingCartService.clear();
            showInfoMessage("Deine Bestellung wurde erfolgreich abgeschickt!");

        } else {
            showInfoMessage("Warenkorb ist leer!");
        }
    }

    public List<PurchaseitemDTO> getPurchaseitemList() {
        purchaseitemList = shoppingCartService.getPurchaseitems();
        return purchaseitemList;
    }

    private void showInfoMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}
