package com.appworkstips.commands;

import com.appworkstips.GenericService;
import com.appworkstips.ISoapMessage;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAllCategoryEntity extends GenericService implements ISoapMessage {
    private static final Logger LOGGER = Logger.getLogger(GetAllCategoryEntity.class.getSimpleName());

    private final String token;
    private final int offset;
    private final int limit;

    public GetAllCategoryEntity(String token, int offset, int limit) {
        this.token = token;
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[TOKEN]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <AllCategories xmlns="http://schemas/AppWorksTipsAppWorks/category/operations">
     * <ns0:Cursor xmlns:ns0="http://schemas.opentext.com/bps/entity/core" offset="0" limit="100" />
     * </AllCategories>
     * </SOAP:Body>
     * </SOAP:Envelope>
     */
    @Override
    public SOAPMessage buildSoapMessage() {
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            ServiceUtils.addAuthenticationHeader(soapEnvelope, token);

            SOAPBody soapBody = soapEnvelope.getBody();

            QName operationGetAllCategory = new QName("http://schemas/AppWorksTipsAppWorks/category/operations", "AllCategories");
            SOAPBodyElement operationElementGetAllCategory = soapBody.addBodyElement(operationGetAllCategory);

            QName operationCursor = new QName("http://schemas.opentext.com/bps/entity/core", "Cursor");
            SOAPElement elementCategoryRead = operationElementGetAllCategory.addChildElement(operationCursor);

            elementCategoryRead.addAttribute(new QName("offset"), String.valueOf(offset));
            elementCategoryRead.addAttribute(new QName("limit"), String.valueOf(limit));

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
