package com.appworkstips.utils;

import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Via SOAP: http://192.168.56.115:8080/home/appworks_tips/services/
 * Invoking AppWorks Platform REST APIs requires BPMService.war, but is not available yet for 16.6
 * https://forums.opentext.com/forums/discussion/comment/932897#Comment_932897
 *
 */

public class Authentication {
    private static final Logger LOGGER = Logger.getLogger(Authentication.class.getSimpleName());

    /**
     * Make a ReST call to OTDS passing in OTDS credentials to get an OTDS ticket

     * POST: http(s)://[OTDS-FQDN]:[OTDS-Port]/otds/rest/authentication/credentials
     * Body: {"userName": "[OTDS-Username]", "password": "[OTDS-Password]","targetResourceId":"[OTDS-AppWorks-ResourceId]"}

     * POST with data: http://192.168.56.115:8181/otdsws/rest/authentication/credentials
     * Body with data1: {"userName": "otdsdev@AppWorks Platform Partition", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}
     * Body with data2: {"userName": "otadmin@otds.admin", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}

     * @return OTDS Ticket
     */
    static String getOTDSTicket() {
        try {
            URL url = new URL(PropertiesUtils.getProperyValue("authentication_url"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(PropertiesUtils.getProperyValue("input_json").getBytes());
            outputStream.flush();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);


            JSONObject jsonObject = new JSONObject(reader.readLine());
            log(jsonObject);
            return jsonObject.getString("ticket");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    private static void log(JSONObject jsonObject) {
        jsonObject.keySet().forEach(keyStr -> {
            Object keyvalue = jsonObject.get(keyStr);
            LOGGER.info(String.format("%s == %s", keyStr, keyvalue));
        });
    }

    /**
     * Make a SOAP call to the platform gateway
     * <p>
     * POST: http(s)://[AppWorks-FQDN]:[AppWorks-Port]/home/system/com.eibus.web.soap.Gateway.wcp/com.eibus.web.soap.Gateway.wcp?o=system,cn=cordys,cn=defaultinst,o=otdemo.net
     * XML data where [Token] is the result from getOTDSTicket():
     * <?xml version="1.0"?>
     * <SOAP:Envelope xmlns:SOAP="http://schemas.xmlsoap.org/soap/envelope/">
     * <SOAP:Header>
     * <OTAuthentication xmlns="urn:api.bpm.opentext.com">
     * <AuthenticationToken>[Token]</AuthenticationToken>
     * </OTAuthentication>
     * </SOAP:Header>
     * <SOAP:Body>
     * <samlp:Request xmlns:samlp="urn:oasis:names:tc:SAML:1.0:protocol" MajorVersion="1" MinorVersion="1" IssueInstant="2014-05-20T15:29:49.156Z" RequestID="a5470c392e-264e-9537-56ac-4397b1b416d">
     * <samlp:AuthenticationQuery>
     * <saml:Subject xmlns:saml="urn:oasis:names:tc:SAML:1.0:assertion">
     * <saml:NameIdentifier Format="urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified"/>
     * </saml:Subject>
     * </samlp:AuthenticationQuery>
     * </samlp:Request>
     * </SOAP:Body>
     * </SOAP:Envelope>
     * <p>
     * POST with data: http://192.168.56.115:8080/home/system/com.eibus.web.soap.Gateway.wcp/com.eibus.web.soap.Gateway.wcp?o=system,cn=cordys,cn=defaultInst,o=mydomain.com
     *
     * @return SAML Assertion Artifact
     */
    static String getSAMLAssertionArtifact(String token) {
        try {
            URL url = new URL(PropertiesUtils.getProperyValue("saml_url"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();

            SOAPHeader soapHeader = soapEnvelope.getHeader();
            SOAPBody soapBody = soapEnvelope.getBody();

            SOAPFactory soapFactory = SOAPFactory.newInstance();

            QName OTAuthenticationName = new QName("urn:api.bpm.opentext.com", "OTAuthentication");
            SOAPHeaderElement OTAuthenticationElement = soapHeader.addHeaderElement(OTAuthenticationName);
            Name authenticationTokenName = soapFactory.createName("AuthenticationToken");
            SOAPElement authenticationTokenElement = OTAuthenticationElement.addChildElement(authenticationTokenName);
            authenticationTokenElement.setTextContent(token);

            Name requestName = soapFactory.createName("Request", "samlp", "urn:oasis:names:tc:SAML:1.0:protocol");
            SOAPBodyElement requestElement = soapBody.addBodyElement(requestName);
            requestElement.setAttribute("MajorVersion", "1");
            requestElement.setAttribute("MinorVersion", "1");

            Name authenticationQueryName = soapFactory.createName("AuthenticationQuery", "samlp", "urn:oasis:names:tc:SAML:1.0:protocol");
            SOAPElement authenticationQueryElement = requestElement.addChildElement(authenticationQueryName);

            Name subjectName = soapFactory.createName("Subject", "saml", "urn:oasis:names:tc:SAML:1.0:assertion");
            SOAPElement subjectElement = authenticationQueryElement.addChildElement(subjectName);

            Name nameIdentifierName = soapFactory.createName("NameIdentifier", "saml", "urn:oasis:names:tc:SAML:1.0:assertion");
            SOAPElement nameIdentifierElement = subjectElement.addChildElement(nameIdentifierName);
            nameIdentifierElement.setAttribute("Format", "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapMessage.writeTo(out);
            String inputString = new String(out.toByteArray());
            String formattedSOAPRequest = formatXML(inputString);
            LOGGER.info(formattedSOAPRequest);

            byte[] buffer = inputString.getBytes();
            byteArrayOutputStream.write(buffer);

            byte[] byteArray = byteArrayOutputStream.toByteArray();
            connection.setRequestProperty("Content-Length", String.valueOf(byteArray.length));
            connection.setRequestProperty("SOAPAction", "AuthenticationQuery");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(byteArray);
            outputStream.close();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String responseString = "";
            String outputString = "";
            while ((responseString = bufferedReader.readLine()) != null) {
                outputString = outputString + responseString;
            }
            String formattedSOAPResponse = formatXML(outputString);
            LOGGER.info(formattedSOAPResponse);
        } catch (IOException | SOAPException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    private static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    private static Document parseXmlFile(String unformattedXml) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(unformattedXml));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
