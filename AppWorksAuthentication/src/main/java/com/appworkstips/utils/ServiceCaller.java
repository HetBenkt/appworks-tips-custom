package com.appworkstips.utils;

import com.appworkstips.models.User;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.appworkstips.services.documentum.utils.PropertiesUtils.getProperyValue;
import static com.appworkstips.utils.Authentication.createConnection;

class ServiceCaller {
    private static final Logger LOGGER = Logger.getLogger(ServiceCaller.class.getSimpleName());

    private ServiceCaller() {
    }

    /**
     * @param token    is the login token that contains the login credentials
     * @param contains is the filter input for the get all users operation
     * @return the list of users from the call
     */
    static List<User> getAllUsers(String token, String contains) {
        ArrayList<User> result = new ArrayList<>();
        try {
            HttpURLConnection connection = createConnection(getProperyValue("soap_url"), "POST", "text/xml; charset=utf-8");
            SOAPMessage soapMessage = buildSoapMessage(token, contains);

            if (connection == null || soapMessage == null) {
                return result;
            } else {
                SOAPMessage soapMessageResponse = callService(connection, soapMessage);
                result.addAll(parseToList(soapMessageResponse));
                connection.disconnect();
                return result;
            }
        } catch (IOException | SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    private static List<User> parseToList(SOAPMessage soapMessageResponse) throws SOAPException {
        ArrayList<User> result = new ArrayList<>();
        SOAPBody soapBody = soapMessageResponse.getSOAPBody();
        Iterator childElements = soapBody.getChildElements();
        while (childElements.hasNext()) {
            Element next = (Element) childElements.next();
            NodeList users = next.getChildNodes();
            for (int i = 0; i < users.getLength(); i++) {
                User user = new User();
                Node itemUser = users.item(i);
                NodeList userData = itemUser.getChildNodes();
                for (int j = 0; j < userData.getLength(); j++) {
                    Node itemData = userData.item(j);
                    String nodeValue = itemData.getNodeName();
                    String textContent = itemData.getTextContent();
                    if (nodeValue.equals("FullName")) {
                        user.setFullName(textContent);
                    }
                    if (nodeValue.contains(":Name")) {
                        user.setName(textContent);
                    }
                }
                result.add(user);
            }
        }
        return result;
    }

    private static SOAPMessage callService(HttpURLConnection connection, SOAPMessage soapMessage) throws IOException, SOAPException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        byte[] byteArray = out.toByteArray();

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(byteArray);

        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result = bufferedReader.readLine();

        InputStream is = new ByteArrayInputStream(result.getBytes());
        SOAPMessage response = MessageFactory.newInstance().createMessage(null, is);

        out.close();
        outputStream.close();
        bufferedReader.close();
        inputStreamReader.close();

        return response;
    }

    /**
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[TOKEN]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <GetAllUsers xmlns="http://schemas/OpenTextEntityIdentityComponents/User/operations">
     * <contains></contains>
     * </GetAllUsers>
     * </SOAP:Body>
     * </SOAP:Envelope>
     *
     * @param token    is the login token that contains the login credentials
     * @param contains is the filter input for the get all users operation
     * @return the SOAPMessage object to sent out
     */
    private static SOAPMessage buildSoapMessage(String token, String contains) {
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            addAuthenticationHeader(soapEnvelope, token);

            SOAPBody soapBody = soapEnvelope.getBody();

            QName operationGetAllUsers = new QName("http://schemas/OpenTextEntityIdentityComponents/User/operations", "GetAllUsers");
            SOAPBodyElement operationElementGetAllUsers = soapBody.addBodyElement(operationGetAllUsers);

            SOAPElement stringParamElement = operationElementGetAllUsers.addChildElement("contains");
            stringParamElement.setTextContent(contains);

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    private static void addAuthenticationHeader(SOAPEnvelope soapEnvelope, String token) throws SOAPException {
        SOAPHeader soapHeader = soapEnvelope.getHeader();
        QName otAuthenticationName = new QName("urn:api.bpm.opentext.com", "OTAuthentication");
        SOAPHeaderElement otAuthenticationElement = soapHeader.addHeaderElement(otAuthenticationName);
        SOAPElement authenticationTokenElement = otAuthenticationElement.addChildElement("AuthenticationToken");
        authenticationTokenElement.setTextContent(token);
    }
}
