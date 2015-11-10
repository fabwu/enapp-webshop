package ch.hslu.edu.enapp.webshop.common;

import javax.ejb.Local;

@Local
public interface PaymentServiceLocal {

    void sendRequest();

}
