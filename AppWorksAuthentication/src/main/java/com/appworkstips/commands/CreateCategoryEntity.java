package com.appworkstips.commands;

import com.appworkstips.GenericService;
import com.appworkstips.ISoapMessage;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateCategoryEntity extends GenericService implements ISoapMessage {
    private static final Logger LOGGER = Logger.getLogger(CreateCategoryEntity.class.getSimpleName());

    private final String token;
    private final boolean isEnabled;
    private final String name;
    private final String description;

    public CreateCategoryEntity(String token, boolean isEnabled, String name, String description) {
        this.token = token;
        this.isEnabled = isEnabled;
        this.name = name;
        this.description = description;
    }

    /**
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[TOKEN]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <Createcategory xmlns="http://schemas/AppWorksTipsAppWorks/category/operations">
     * <ns0:category-create xmlns:ns0="http://schemas/AppWorksTipsAppWorks/category">
     * <ns0:cat_is_enabled>true</ns0:cat_is_enabled>
     * <ns0:cat_name>your_name</ns0:cat_name>
     * <ns0:cat_description>your_description</ns0:cat_description>
     * </ns0:category-create>
     * </Createcategory>
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

            QName operationCreateCategory = new QName("http://schemas/AppWorksTipsAppWorks/category/operations", "Createcategory");
            SOAPBodyElement operationElementCreateCategory = soapBody.addBodyElement(operationCreateCategory);

            QName operationCategoryCreate = new QName("http://schemas/AppWorksTipsAppWorks/category", "category-create");
            SOAPElement elementCategoryCreate = operationElementCreateCategory.addChildElement(operationCategoryCreate);
            SOAPElement catIsEnabled = elementCategoryCreate.addChildElement("cat_is_enabled");
            catIsEnabled.setTextContent(String.valueOf(isEnabled));
            SOAPElement catName = elementCategoryCreate.addChildElement("cat_name");
            catName.setTextContent(name);
            SOAPElement catDescription = elementCategoryCreate.addChildElement("cat_description");
            catDescription.setTextContent(description);

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
