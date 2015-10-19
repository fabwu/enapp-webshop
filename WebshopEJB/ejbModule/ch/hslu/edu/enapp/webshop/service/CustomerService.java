package ch.hslu.edu.enapp.webshop.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
@LocalBean
public class CustomerService implements CustomerServiceLocal {

    /**
     * Default constructor.
     */
    public CustomerService() {
        // TODO Auto-generated constructor stub
    }

}
