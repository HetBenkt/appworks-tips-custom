package com.appworkstips.commands;

import com.appworkstips.GenericService;
import com.appworkstips.ISoapMessage;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCategoryEntity extends GenericService implements ISoapMessage {
    private static final Logger LOGGER = Logger.getLogger(DeleteCategoryEntity.class.getSimpleName());

    private final String token;
    private final String itemId;

    public DeleteCategoryEntity(String token, String itemId) {
        this.token = token;
        this.itemId = itemId;
    }

    /**
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[TOKEN]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <Deletecategory xmlns="http://schemas/AppWorksTipsAppWorks/category/operations">
     * <ns0:category xmlns:ns0="http://schemas/AppWorksTipsAppWorks/category">
     * <ns0:category-id>
     * <ns0:ItemId></ns0:ItemId>
     * </ns0:category-id>
     * </ns0:category>
     * </Deletecategory>
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

            QName operationDeleteCategory = new QName("http://schemas/AppWorksTipsAppWorks/category/operations", "Deletecategory");
            SOAPBodyElement operationElementDeleteCategory = soapBody.addBodyElement(operationDeleteCategory);

            QName operationCategory = new QName("http://schemas/AppWorksTipsAppWorks/category", "category");
            SOAPElement elementCategoryDelete = operationElementDeleteCategory.addChildElement(operationCategory);

            QName operationCategoryRead = new QName("http://schemas/AppWorksTipsAppWorks/category", "category-id");
            SOAPElement elementCategoryRead = elementCategoryDelete.addChildElement(operationCategoryRead);

            SOAPElement soapElementItemId = elementCategoryRead.addChildElement("ItemId");
            soapElementItemId.setTextContent(itemId);

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
