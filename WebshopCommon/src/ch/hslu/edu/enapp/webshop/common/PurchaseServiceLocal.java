package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

@Local
public interface PurchaseServiceLocal {

    void order(List<PurchaseitemDTO> purchaseitemDtoList, CustomerDTO customerDTO);

    List<PurchaseDTO> getPurchasesFromCurrentCustomer();

}
