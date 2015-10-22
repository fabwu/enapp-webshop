package ch.hslu.edu.enapp.webshop.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    // private String username;
    //
    // @PostConstruct
    // public void init() {
    // username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    // }

    public void logout(final ActionEvent event) throws IOException {
        final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath()
                + "/ibm_security_logout?logoutExitPage=/index.xhtml");
    }

}
