package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;

public class CustomerConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    public Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setAddress(dto.getAddress());
        customer.setCustomerid(dto.getCustomerid());
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setPassword(dto.getPassword());
        // TODO customer.setPurchases(dto.getPurchases());
        customer.setUsername(dto.getUsername());
        return customer;
    }

    public CustomerDTO convertToDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setAddress(customer.getAddress());
        dto.setCustomerid(customer.getCustomerid());
        dto.setEmail(customer.getEmail());
        dto.setName(customer.getName());
        dto.setPassword(customer.getPassword());
        // TODO dto.setPurchases(customer.getPurchases());
        dto.setUsername(customer.getUsername());
        return dto;
    }

}
