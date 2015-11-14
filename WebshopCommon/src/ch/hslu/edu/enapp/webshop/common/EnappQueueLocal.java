package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

@Local
public interface EnappQueueLocal {

    String sendMsg(String payId, int purchaseId, List<PurchaseitemDTO> purchaseitemDtoList, CustomerDTO customerDTO);

}
