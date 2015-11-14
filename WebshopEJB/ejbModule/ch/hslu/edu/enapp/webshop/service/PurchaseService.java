package ch.hslu.edu.enapp.webshop.service;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.hslu.edu.enapp.webshop.common.PaymentServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.edu.enapp.webshop.converter.PurchaseConverter;
import ch.hslu.edu.enapp.webshop.converter.PurchaseitemConverter;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;
import ch.hslu.edu.enapp.webshop.message.CustomerMessage;
import ch.hslu.edu.enapp.webshop.message.LineMessage;
import ch.hslu.edu.enapp.webshop.message.PurchaseMessage;

/**
 * Session Bean implementation class Purchase
 */
@Stateless
@LocalBean
public class PurchaseService implements PurchaseServiceLocal {

    private static final String STUDENT = "tdwuethr";

    @Resource(name = "EnappQueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "EnappQueue")
    private Queue queue;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    CustomerConverter customerConverter;

    @Inject
    PurchaseitemConverter purchaseitemConverter;

    @Inject
    PurchaseConverter purchaseConverter;

    @Inject
    PaymentServiceLocal paymentService;

    @Override
    public void order(List<PurchaseitemDTO> purchaseitemDtoList, CustomerDTO customerDTO) {

        int purchaseId = persistPurchase(customerDTO, purchaseitemDtoList);

        String payId = paymentService.sendRequest(String.valueOf(purchaseId));

        PurchaseMessage purchaseMessage = new PurchaseMessage();
        purchaseMessage.setPayId(payId);
        purchaseMessage.setPurchaseId(purchaseId);
        purchaseMessage.setStudent(STUDENT);
        // TODO Set Total Price
        purchaseMessage.setTotalPrice(new BigDecimal(10));
        purchaseMessage.setDate(new Date());

        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setName(customerDTO.getName());
        customerMessage.setAddress(customerDTO.getAddress());
        customerMessage.setShopLoginname(customerDTO.getUsername());
        purchaseMessage.setCustomer(customerMessage);

        for (PurchaseitemDTO purchaseitemDTO : purchaseitemDtoList) {
            LineMessage lineMessage = new LineMessage();
            lineMessage.setMsDynNAVItemNo(purchaseitemDTO.getProduct().getNo());
            lineMessage.setQuantity(purchaseitemDTO.getQuantity());
            lineMessage.setTotalLinePrice(purchaseitemDTO.getTotalprice());
            purchaseMessage.addLine(lineMessage);
        }

        String correlationId = sendMsg(purchaseMessage);
        System.out.println(correlationId);
    }

    private int persistPurchase(CustomerDTO customerDTO, List<PurchaseitemDTO> purchaseitemDtoList) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customerConverter.convertToEntity(customerDTO));
        purchase.setState("999");
        purchase.setDatetime(getCurrentTimestamp());

        entityManager.persist(purchase);

        List<Purchaseitem> purchaseitemList = addPurchase(
                purchaseitemConverter.convertListToEntity(purchaseitemDtoList), purchase);
        purchase.setPurchaseitems(purchaseitemList);

        entityManager.flush();

        return purchase.getPurchaseid();
    }

    private String sendMsg(PurchaseMessage purchaseMessage) {
        String correlationId = "";

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            String purchaseMessageText = getPurchaseMessageAsString(purchaseMessage);

            TextMessage textMessage = session.createTextMessage(purchaseMessageText);
            textMessage.setStringProperty("MessageFormat", "Version 1.5");
            correlationId = UUID.randomUUID().toString();
            textMessage.setJMSCorrelationID(correlationId);

            messageProducer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
            correlationId = "";
        }

        return correlationId;
    }

    private String getPurchaseMessageAsString(PurchaseMessage purchaseMessage) {
        String purchaseMessageText = "";

        try {
            // TODO Inject this shit!
            JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseMessage.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(purchaseMessage, writer);
            purchaseMessageText = writer.toString();
            System.out.println(purchaseMessageText);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return purchaseMessageText;
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    private List<Purchaseitem> addPurchase(List<Purchaseitem> purchaseitems, Purchase purchase) {
        ArrayList<Purchaseitem> purchaseitemsWithPurchase = new ArrayList<Purchaseitem>();

        for (Purchaseitem purchaseitem : purchaseitems) {
            purchaseitem.setPurchase(purchase);
            purchaseitemsWithPurchase.add(purchaseitem);
        }

        return purchaseitemsWithPurchase;
    }

    @Override
    public List<PurchaseDTO> getPurchases() {
        List<Purchase> purchases = entityManager.createNamedQuery("getPurchase", Purchase.class).getResultList();
        for (Purchase purchase : purchases) {
            purchase.getPurchaseitems().size();
        }
        return purchaseConverter.convertListToDto(purchases);
    }

}
