package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;

public class CustomerConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    public Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setCustomerid(dto.getCustomerid());
        customer.setDynNavNo(dto.getDynNavNo());
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setPassword(dto.getPassword());
        // TODO customer.setPurchases(dto.getPurchases());
        customer.setUsername(dto.getUsername());
        return customer;
    }

    public CustomerDTO convertToDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerid(customer.getCustomerid());
        dto.setDynNavNo(customer.getDynNavNo());
        dto.setAddress(customer.getAddress());
        dto.setEmail(customer.getEmail());
        dto.setName(customer.getName());
        dto.setPassword(customer.getPassword());
        // TODO dto.setPurchases(customer.getPurchases());
        dto.setUsername(customer.getUsername());
        return dto;
    }

    public List<CustomerDTO> convertListToDto(List<Customer> entityList) {
        List<CustomerDTO> dtoList = new ArrayList<>();

        for (Customer customer : entityList) {
            CustomerDTO dto = convertToDto(customer);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
