package com.appworkstips.commands;

import com.appworkstips.GenericService;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAllUsers extends GenericService {
    private static final Logger LOGGER = Logger.getLogger(GetAllUsers.class.getSimpleName());

    private final String token;
    private final String contains;

    /**
     * Constructor to init fields
     *
     * @param token    is the login token that contains the login credentials
     * @param contains is the filter input for the minimal value
     */
    public GetAllUsers(String token, String contains) {
        this.token = token;
        this.contains = contains;
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
     * @return the SOAPMessage object to sent out
     */
    public SOAPMessage buildSoapMessage() {
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            ServiceUtils.addAuthenticationHeader(soapEnvelope, token);

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
}
