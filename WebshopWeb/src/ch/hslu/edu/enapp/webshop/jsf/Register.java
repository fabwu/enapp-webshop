package ch.hslu.edu.enapp.webshop.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Named
@SessionScoped
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CustomerServiceLocal customerService;

    private CustomerDTO customer;

    public Register() {
        customer = new CustomerDTO();
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void createCustomer() throws IOException {

        try {
            customerService.createCustomer(customer);

            customer = new CustomerDTO();
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("login.xhtml");
            showInfoMessage("Du wurdest erfolgreich registriert");
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
