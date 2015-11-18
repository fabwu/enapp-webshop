package ch.hslu.edu.enapp.webshop.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.edu.enapp.webshop.entity.Authgroup;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.messages.SalesOrderMessage;

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

    @Inject
    StatusCheckService statusCheckService;

    @Override
    public void createCustomer(CustomerDTO customer) {
        List<Customer> existingCustomer = entityManager.createNamedQuery("getCustomerByUsername", Customer.class)
                .setParameter("username", customer.getUsername()).getResultList();

        if (existingCustomer.size() != 0) {
            throw new IllegalArgumentException("Username already exists");
        }

        Authgroup userGroup = entityManager.createNamedQuery("getAuthgroupByGroupname", Authgroup.class)
                .setParameter("groupname", "userRole").getSingleResult();

        Customer entity = converter.convertToEntity(customer);
        entity.setAuthgroups(Collections.singletonList(userGroup));

        entityManager.persist(entity);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        Customer entity = converter.convertToEntity(customerDTO);
        entityManager.merge(entity);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> entityList = entityManager.createNamedQuery("getCustomer", Customer.class).getResultList();
        List<CustomerDTO> dtoList = converter.convertListToDto(entityList);
        return dtoList;
    }

    @Override
    public CustomerDTO getCurrentCustomer() {

        CustomerDTO customerDto = null;
        Customer customer = getCurrentCustomerEntity();

        if (customer != null) {
            customerDto = converter.convertToDto(customer);
        }

        return customerDto;
    }

    @Asynchronous
    @Override
    public void updateDynNAVNo(String messageId) {
        SalesOrderMessage salesOrder = statusCheckService.getSalesOrder(messageId);

        Customer customer = getCurrentCustomerEntity();
        if (customer.getDynNavNo() == null) {
            System.out.println("Upadte!");
            customer.setDynNavNo(salesOrder.getDynNAVCustomerNo());
            entityManager.merge(customer);
            entityManager.flush();
        }

    }

    private Customer getCurrentCustomerEntity() {
        String principalName = context.getCallerPrincipal().getName();
        Customer customer = null;

        if (!principalName.equalsIgnoreCase("UNAUTHENTICATED")) {
            customer = entityManager.createNamedQuery("getCustomerByUsername", Customer.class)
                    .setParameter("username", principalName).getSingleResult();
        }

        return customer;
    }

}
