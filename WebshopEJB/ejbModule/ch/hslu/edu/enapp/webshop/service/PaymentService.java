package ch.hslu.edu.enapp.webshop.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import ch.hslu.edu.enapp.webshop.common.PaymentServiceLocal;

/**
 * Session Bean implementation class PaymentService
 */
@Stateless
@LocalBean
public class PaymentService implements PaymentServiceLocal {

    private final String AMOUNT = "1";
    private final String CARDNO = "5399999999999999";
    private final String CURRENCY = "CHF";
    private final String CVC = "123";
    private final String ED = "10/18";
    private final String PSPID = "HSLUiCompany";
    private final String PSWD = "ds2H9ZV.p!8r";
    private final String USERID = "enappstudents";

    private final String SHA_PASSPHRASE = "hslu!comp@ny.websh0p";

    @Override
    public String sendRequest(String orderId) {
        RestClient client = new RestClient();
        Resource webResource = client.resource("https://e-payment.postfinance.ch/ncol/test/orderdirect.asp");

        String shasign = getShasign(orderId);

        String params = getParams(orderId, shasign);

        ClientResponse response = webResource.accept("application/x-www-form-urlencoded").post(ClientResponse.class,
                params);

        String string = response.getEntity(String.class);
        String paymentId = string.split("PAYID=")[1].substring(1, 9);

        return paymentId;
    }

    private String getParams(String orderId, String shasign) {
        StringBuilder builder = new StringBuilder();

        builder.append("AMOUNT=" + AMOUNT + "&");
        builder.append("CARDNO=" + CARDNO + "&");
        builder.append("CURRENCY=" + CURRENCY + "&");
        builder.append("CVC=" + CVC + "&");
        builder.append("ED=" + ED + "&");
        builder.append("ORDERID=" + orderId + "&");
        builder.append("PSPID=" + PSPID + "&");
        builder.append("PSWD=" + PSWD + "&");
        builder.append("USERID=" + USERID + "&");
        builder.append("SHASIGN=" + shasign);

        return builder.toString();
    }

    private String getShasign(String orderId) {
        final StringBuilder builder = new StringBuilder();

        builder.append("AMOUNT=" + AMOUNT + SHA_PASSPHRASE);
        builder.append("CARDNO=" + CARDNO + SHA_PASSPHRASE);
        builder.append("CURRENCY=" + CURRENCY + SHA_PASSPHRASE);
        builder.append("CVC=" + CVC + SHA_PASSPHRASE);
        builder.append("ED=" + ED + SHA_PASSPHRASE);
        builder.append("ORDERID=" + orderId + SHA_PASSPHRASE);
        builder.append("PSPID=" + PSPID + SHA_PASSPHRASE);
        builder.append("PSWD=" + PSWD + SHA_PASSPHRASE);
        builder.append("USERID=" + USERID + SHA_PASSPHRASE);

        String hashString = builder.toString();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = digest.digest(hashString.getBytes());
            return byteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
