package ch.hslu.edu.enapp.webshop.message;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.hslu.edu.enapp.webshop.common.EnappQueueLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;

/**
 * Session Bean implementation class EnappQueue
 */
@Stateless
@LocalBean
public class EnappQueue implements EnappQueueLocal {

    private static final String STUDENT = "tdwuethr";

    @Resource(name = "EnappQueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "EnappQueue")
    private Queue queue;

    @Override
    public String sendMsg(String payId, int purchaseId, List<PurchaseitemDTO> purchaseitemDtoList,
            CustomerDTO customerDTO) {
        PurchaseMessage purchaseMessage = preparePurchaseMessage(payId, purchaseId, purchaseitemDtoList, customerDTO);
        String messageId = sendMsg(purchaseMessage);
        return messageId;
    }

    private PurchaseMessage preparePurchaseMessage(String payId, int purchaseId,
            List<PurchaseitemDTO> purchaseitemDtoList, CustomerDTO customerDTO) {
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

        return purchaseMessage;
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
            JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseMessage.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(purchaseMessage, writer);
            purchaseMessageText = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return purchaseMessageText;
    }

}
