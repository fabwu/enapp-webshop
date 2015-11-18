package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Local
public interface CustomerServiceLocal {

    void createCustomer(CustomerDTO customer);

    void updateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCurrentCustomer();

    void updateDynNAVNo(String messageId);

}
