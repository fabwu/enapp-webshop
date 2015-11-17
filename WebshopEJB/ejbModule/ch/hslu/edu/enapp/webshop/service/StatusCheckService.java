package ch.hslu.edu.enapp.webshop.service;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.messages.SalesOrderMessage;

/**
 * Session Bean implementation class StatusCheckService
 */
@Stateless
@LocalBean
public class StatusCheckService {

    private static final String URL = "http://enapp-daemons.el.eee.intern:9080/EnappDaemonWeb/rest/salesorder/corr/";

    private static final int WAIT_IN_SECONDS = 5;
    private static final int MAX_RETRY = 5;

    @PersistenceContext
    EntityManager entityManager;

    @Schedule(hour = "1")
    @Asynchronous
    public void updatePurchaseStatus() {
        List<Purchase> resultList = entityManager.createNamedQuery("getPurchase", Purchase.class).getResultList();
        for (Purchase purchase : resultList) {
            SalesOrderMessage salesOrder = getSalesOrder(purchase.getMessageid());

            if (salesOrder != null) {
                purchase.setState(salesOrder.getOrderStatus());
            }
        }
        entityManager.flush();
    }

    public SalesOrderMessage getSalesOrder(String messageId) {
        RestClient restClient = new RestClient();
        String url = URL + messageId;
        Resource resource = restClient.resource(url);

        SalesOrderMessage salesOrder = null;
        int retry = 0;

        while (retry < MAX_RETRY && salesOrder == null) {
            try {
                ClientResponse clientResponse = resource.get(ClientResponse.class);
                salesOrder = clientResponse.getEntity(SalesOrderMessage.class);
            } catch (Exception e) {
                try {
                    Thread.sleep(1000 * WAIT_IN_SECONDS);
                } catch (InterruptedException e1) {
                    // Ignore...
                }
                retry++;
            }
        }
        return salesOrder;
    }
}
