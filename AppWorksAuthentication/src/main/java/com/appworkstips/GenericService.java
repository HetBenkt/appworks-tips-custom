package com.appworkstips;

import com.appworkstips.utils.ResultParser;
import com.appworkstips.utils.ServiceUtils;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.appworkstips.services.documentum.utils.PropertiesUtils.getProperyValue;
import static com.appworkstips.utils.Authentication.createConnection;

public class GenericService implements IServiceCommand {
    private static final Logger LOGGER = Logger.getLogger(GenericService.class.getSimpleName());
    private ResultParser resultParser = ResultParser.getInstance();

    @Override
    public void execute(SOAPMessage soapMessage) {
        HttpURLConnection connection = createConnection(getProperyValue("soap_url"), "POST", "text/xml; charset=utf-8");

        if (connection == null || soapMessage == null) {
            LOGGER.info("No connection or soap-message available");
        } else {
            try {
                SOAPMessage soapMessageResponse = ServiceUtils.callService(connection, soapMessage);
                resultParser.setSoapMessage(soapMessageResponse);
            } catch (IOException | SOAPException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } finally {
                connection.disconnect();
            }
        }
    }
}
