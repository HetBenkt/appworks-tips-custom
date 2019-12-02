package com.appworkstips.utils;

import com.appworkstips.models.User;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ResultParser {
    private static final Logger LOGGER = Logger.getLogger(ResultParser.class.getSimpleName());
    private static ResultParser instance;
    private SOAPMessage soapMessage;

    public static ResultParser getInstance() {
        if (instance == null)
            instance = new ResultParser();
        return instance;
    }

    List<User> parseToList() throws SOAPException {
        List<User> userList = new ArrayList<>();
        SOAPBody soapBody = soapMessage.getSOAPBody();
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
                userList.add(user);
            }
        }
        return userList;
    }

    String getValue(String expression) throws XPathExpressionException, SOAPException {
        LOGGER.info(expression);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        return xPath.compile(expression).evaluate(soapMessage.getSOAPBody());
    }

    SOAPMessage getSoapMessage() {
        return this.soapMessage;
    }

    public void setSoapMessage(SOAPMessage soapMessage) {
        this.soapMessage = soapMessage;
    }

}
