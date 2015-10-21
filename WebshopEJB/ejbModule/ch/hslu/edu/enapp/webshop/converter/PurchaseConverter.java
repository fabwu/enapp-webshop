package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

public class PurchaseConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CustomerConverter customerConverter;

    @Inject
    PurchaseitemConverter purchaseitemConverter;

    public Purchase convertToEntity(PurchaseDTO dto) {
        Purchase purchase = new Purchase();

        Customer customer = customerConverter.convertToEntity(dto.getCustomer());
        purchase.setCustomer(customer);

        purchase.setDatetime(dto.getDatetime());
        purchase.setPurchaseid(dto.getPurchaseid());

        List<Purchaseitem> purchaseitems = purchaseitemConverter.convertListToEntity(dto.getPurchaseitems());
        purchase.setPurchaseitems(purchaseitems);

        purchase.setState(dto.getState());
        return purchase;
    }

    public PurchaseDTO convertToDto(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();

        CustomerDTO customerDto = customerConverter.convertToDto(purchase.getCustomer());
        dto.setCustomer(customerDto);

        dto.setDatetime(purchase.getDatetime());
        dto.setPurchaseid(purchase.getPurchaseid());

        List<PurchaseitemDTO> purchaseitemDtos = purchaseitemConverter.convertListToDto(purchase.getPurchaseitems());
        dto.setPurchaseitems(purchaseitemDtos);

        dto.setState(purchase.getState());
        return dto;
    }

    public List<Purchase> convertListToEntity(List<PurchaseDTO> dtoList) {
        List<Purchase> entityList = new ArrayList<>();

        for (PurchaseDTO dto : dtoList) {
            Purchase entity = convertToEntity(dto);
            entityList.add(entity);
        }

        return entityList;
    }

    public List<PurchaseDTO> convertListToDto(List<Purchase> entityList) {
        List<PurchaseDTO> dtoList = new ArrayList<>();

        for (Purchase entity : entityList) {
            PurchaseDTO dto = convertToDto(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
