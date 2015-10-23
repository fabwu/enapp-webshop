package ch.hslu.edu.enapp.webshop.service;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.edu.enapp.webshop.entity.Customer;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
@LocalBean
public class CustomerService implements CustomerServiceLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    EJBContext context;

    @Inject
    CustomerConverter converter;

    @Override
    public CustomerDTO getCurrentCustomer() {
        String principalName = context.getCallerPrincipal().getName();

        CustomerDTO customerDto = null;

        if (!principalName.equalsIgnoreCase("UNAUTHENTICATED")) {
            Customer customer = entityManager.createNamedQuery("getCustomerByUsername", Customer.class)
                    .setParameter("username", principalName).getSingleResult();
            customerDto = converter.convertToDto(customer);
        }

        return customerDto;
    }
}
