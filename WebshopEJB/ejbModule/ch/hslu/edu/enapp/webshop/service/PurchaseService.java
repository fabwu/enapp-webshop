package ch.hslu.edu.enapp.webshop.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.PurchaseServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.converter.PurchaseitemConverter;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

/**
 * Session Bean implementation class Purchase
 */
@Stateless
@LocalBean
public class PurchaseService implements PurchaseServiceLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PurchaseitemConverter purchaseitemConverter;

    @Override
    public void order(List<PurchaseitemDTO> purchaseitemDtoList) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(getDummyCustomer());
        purchase.setState("A");
        purchase.setDatetime(getCurrentTimestamp());

        entityManager.persist(purchase);

        List<Purchaseitem> purchaseitemList = addPurchase(
                purchaseitemConverter.convertListToEntity(purchaseitemDtoList), purchase);
        purchase.setPurchaseitems(purchaseitemList);

        entityManager.flush();
    }

    private Customer getDummyCustomer() {
        Customer customer = entityManager.createNamedQuery("getCustomerByUsername", Customer.class)
                .setParameter("username", "muster").getSingleResult();

        return customer;
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    private List<Purchaseitem> addPurchase(List<Purchaseitem> purchaseitems, Purchase purchase) {
        ArrayList<Purchaseitem> purchaseitemsWithPurchase = new ArrayList<Purchaseitem>();

        for (Purchaseitem purchaseitem : purchaseitems) {
            purchaseitem.setPurchase(purchase);
            purchaseitemsWithPurchase.add(purchaseitem);
        }

        return purchaseitemsWithPurchase;
    }

}
