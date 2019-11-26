package com.appworkstips.commands;

import com.appworkstips.IServiceCommand;
import com.appworkstips.exceptions.ServiceException;
import com.appworkstips.utils.ResultParser;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.soap.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.appworkstips.services.documentum.utils.PropertiesUtils.getProperyValue;
import static com.appworkstips.utils.Authentication.createConnection;

public class GetRandomIntValueMinMax implements IServiceCommand {
    private static final Logger LOGGER = Logger.getLogger(GetRandomIntValueMinMax.class.getSimpleName());

    private final String token;
    private final String intMinValue;
    private final String intMaxValue;

    private ResultParser resultParser = ResultParser.getInstance();

    /**
     * Constructor to init fields
     *
     * @param token       is the login token that contains the login credentials
     * @param intMinValue is the filter input for the minimal value
     * @param intMaxValue is the filter input for the maximum value
     */
    public GetRandomIntValueMinMax(String token, String intMinValue, String intMaxValue) {
        this.token = token;
        this.intMinValue = intMinValue;
        this.intMaxValue = intMaxValue;
    }

    @Override
    public void execute() throws ServiceException {
        HttpURLConnection connection = createConnection(getProperyValue("soap_url"), "POST", "text/xml; charset=utf-8");
        SOAPMessage soapMessage = buildSoapMessage();

        if (connection == null || soapMessage == null) {
            throw new ServiceException("No connection or soapmessage available.");
        } else {
            try {
                SOAPMessage soapMessageResponse = ServiceUtils.callService(connection, soapMessage);
                resultParser.setSoapMessage(soapMessageResponse);
                resultParser.soapMessageToString();
            } catch (IOException | SOAPException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } finally {
                connection.disconnect();
            }
        }
    }

    /**
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/" xmlns:app="http://schemas.cordys.com/AppWorksServices">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[TOKEN]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <app:getRandomIntValueMinMax>
     * <app:stringParam>0</app:stringParam>
     * <app:stringParam1>120</app:stringParam1>
     * </app:getRandomIntValueMinMax>
     * </SOAP:Body>
     * </SOAP:Envelope>
     *
     * @return the result message
     */
    @Override
    public SOAPMessage buildSoapMessage() {
        final String URI = "http://schemas.cordys.com/AppWorksServices";
        final String PREFIX = "app";

        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            soapEnvelope.addNamespaceDeclaration(PREFIX, URI);
            ServiceUtils.addAuthenticationHeader(soapEnvelope, token);

            SOAPBody soapBody = soapEnvelope.getBody();

            Name operationName = soapFactory.createName("getRandomIntValueMinMax", PREFIX, URI);
            SOAPBodyElement operationElement = soapBody.addBodyElement(operationName);

            Name stringParamName = soapFactory.createName("stringParam", PREFIX, URI);
            SOAPElement stringParamElement = operationElement.addChildElement(stringParamName);
            stringParamElement.setTextContent(intMinValue);
            Name stringParam1Name = soapFactory.createName("stringParam1", PREFIX, URI);
            SOAPElement stringParam1Element = operationElement.addChildElement(stringParam1Name);
            stringParam1Element.setTextContent(intMaxValue);

            return soapMessage;
        } catch (SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
