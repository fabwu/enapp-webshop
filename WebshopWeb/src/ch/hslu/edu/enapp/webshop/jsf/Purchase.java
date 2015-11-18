package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.service.PurchaseService;

@Named
@SessionScoped
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PurchaseService purchaseService;

    private List<PurchaseDTO> purchases;

    public List<PurchaseDTO> getPurchases() {
        purchases = purchaseService.getPurchasesFromCurrentCustomer();
        return purchases;
    }
}
