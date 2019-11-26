package com.appworkstips.utils;

import com.appworkstips.models.User;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.appworkstips.services.documentum.utils.PropertiesUtils.getProperyValue;
import static com.appworkstips.utils.Authentication.createConnection;

class ServiceCaller {
    private static final Logger LOGGER = Logger.getLogger(ServiceCaller.class.getSimpleName());
    private ResultParser resultParser = ResultParser.getInstance();

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
     *
     * @param token       is the login token that contains the login credentials
     * @param isEnabled   is the is_enabled property of the category entity
     * @param name        is the name property of the category entity
     * @param description is the description property of the category entity
     * @return the category ID
     */
    private static SOAPMessage buildSoapMessage(String token, boolean isEnabled, String name, String description) {
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            ServiceUtils.addAuthenticationHeader(soapEnvelope, token);

            SOAPBody soapBody = soapEnvelope.getBody();

            QName operationCreateCategory = new QName("http://schemas/AppWorksTipsAppWorks/category/operations", "Createcategory");
            SOAPBodyElement operationElementCreatecategory = soapBody.addBodyElement(operationCreateCategory);

            QName operationCategoryCreate = new QName("http://schemas/AppWorksTipsAppWorks/category", "category-create");
            SOAPElement elementCategoryCreate = operationElementCreatecategory.addChildElement(operationCategoryCreate);
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

    String createCategoryEntity(String token, boolean isEnabled, String name, String description) {
        try {
            HttpURLConnection connection = createConnection(getProperyValue("soap_url"), "POST", "text/xml; charset=utf-8");
            SOAPMessage soapMessage = buildSoapMessage(token, isEnabled, name, description);

            if (connection == null || soapMessage == null) {
                return "";
            } else {
                SOAPMessage soapMessageResponse = ServiceUtils.callService(connection, soapMessage);
                resultParser.setSoapMessage(soapMessageResponse);
                resultParser.soapMessageToString();
                String result = soapMessageResponse.getSOAPBody().getTextContent();
                connection.disconnect();
                return result;
            }
        } catch (IOException | SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return "";
    }

    /**
     * @param token    is the login token that contains the login credentials
     * @param contains is the filter input for the get all users operation
     * @return the list of users from the call
     */
    List<User> getAllUsers(String token, String contains) {
        List<User> result = new ArrayList<>();
        try {
            HttpURLConnection connection = createConnection(getProperyValue("soap_url"), "POST", "text/xml; charset=utf-8");
            SOAPMessage soapMessage = buildSoapMessage(token, contains);

            if (connection == null || soapMessage == null) {
                return result;
            } else {
                SOAPMessage soapMessageResponse = ServiceUtils.callService(connection, soapMessage);
                resultParser.setSoapMessage(soapMessageResponse);
                resultParser.soapMessageToString();
                result.addAll(resultParser.parseToList());
                connection.disconnect();
                return result;
            }
        } catch (IOException | SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

}
