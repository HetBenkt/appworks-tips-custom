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
    private final String categoryId;

    public DeleteCategoryEntity(String token, String categoryId) {
        this.token = token;
        this.categoryId = categoryId;
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
     * <ns0:Id>PARAMETER</ns0:Id>
     * <ns0:ItemId>PARAMETER</ns0:ItemId>
     * </ns0:category-id>
     * <ns0:cat_id>PARAMETER</ns0:cat_id>
     * <ns0:cat_is_enabled>true</ns0:cat_is_enabled>
     * <ns0:cat_name>PARAMETER</ns0:cat_name>
     * <ns0:cat_description>PARAMETER</ns0:cat_description>
     * <ns0:cat_priority>1</ns0:cat_priority>
     * <ns0:cat_type>PARAMETER</ns0:cat_type>
     * <ns1:Title xmlns:ns1="http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0">
     * <ns1:Value>PARAMETER</ns1:Value>
     * </ns1:Title>
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

            QName operationCategoryCreate = new QName("http://schemas/AppWorksTipsAppWorks/category", "category");
            SOAPElement elementCategoryCreate = operationElementDeleteCategory.addChildElement(operationCategoryCreate);

            //TODO add the other nodes for a delete action

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
