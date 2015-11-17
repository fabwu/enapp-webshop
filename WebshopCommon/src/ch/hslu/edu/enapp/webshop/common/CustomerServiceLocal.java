package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Local
public interface CustomerServiceLocal {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCurrentCustomer();

    void updateCustomer(CustomerDTO customerDTO);

    void updateDynNAVNo(String messageId);

}
