package ch.hslu.edu.enapp.webshop.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.EnappQueueLocal;
import ch.hslu.edu.enapp.webshop.common.PaymentServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.edu.enapp.webshop.converter.PurchaseConverter;
import ch.hslu.edu.enapp.webshop.converter.PurchaseitemConverter;
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
    CustomerConverter customerConverter;

    @Inject
    PurchaseitemConverter purchaseitemConverter;

    @Inject
    PurchaseConverter purchaseConverter;

    @Inject
    PaymentServiceLocal paymentService;

    @Inject
    EnappQueueLocal enappQueue;

    @Inject
    CustomerServiceLocal customerService;

    @Inject
    StatusCheckService statusCheckService;

    @Override
    public void order(List<PurchaseitemDTO> purchaseitemDtoList, CustomerDTO customerDTO) {

        Purchase purchase = persistPurchase(customerDTO, purchaseitemDtoList);

        int purchaseId = purchase.getPurchaseid();

        String payId = paymentService.sendRequest(String.valueOf(purchaseId));

        String messageId = enappQueue.sendMsg(payId, purchaseId, purchaseitemDtoList, customerDTO);

        purchase.setMessageid(messageId);

        entityManager.flush();

        customerService.updateDynNAVNo(messageId);
        statusCheckService.updatePurchaseStatus();
    }

    private Purchase persistPurchase(CustomerDTO customerDTO, List<PurchaseitemDTO> purchaseitemDtoList) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customerConverter.convertToEntity(customerDTO));
        purchase.setState("999");
        purchase.setDatetime(getCurrentTimestamp());

        entityManager.persist(purchase);

        List<Purchaseitem> purchaseitemList = addPurchase(
                purchaseitemConverter.convertListToEntity(purchaseitemDtoList), purchase);
        purchase.setPurchaseitems(purchaseitemList);

        return purchase;
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

    @Override
    public List<PurchaseDTO> getPurchasesFromCurrentCustomer() {
        CustomerDTO currentCustomer = customerService.getCurrentCustomer();

        if (currentCustomer == null) {
            return Collections.emptyList();
        }

        List<Purchase> purchases = entityManager.createNamedQuery("getPurchaseByCustomerBean", Purchase.class)
                .setParameter("customer_customerid", currentCustomer.getCustomerid()).getResultList();

        for (Purchase purchase : purchases) {
            purchase.getPurchaseitems().size();
        }

        return purchaseConverter.convertListToDto(purchases);
    }

}
