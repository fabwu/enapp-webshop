package ch.hslu.edu.enapp.webshop.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Named
@SessionScoped
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CustomerServiceLocal customerService;

    private CustomerDTO customer;

    public CustomerDTO getCustomer() {
        customer = customerService.getCurrentCustomer();
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void updateCustomer() throws IOException {
        try {
            customerService.updateCustomer(customer);

            customer = customerService.getCurrentCustomer();
            showInfoMessage("Dein Profil wurde erfolgreich angepasst");
        } catch (EJBException e) {
            showErrorMessage(e.getCausedByException().getMessage());
        }
    }

    private void showInfoMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    private void showErrorMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}
