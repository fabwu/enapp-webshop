package ch.hslu.edu.enapp.webshop.common;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Local
public interface CustomerServiceLocal {

    CustomerDTO getCurrentCustomer();

}
