package com.appworkstips.utils;

import com.appworkstips.commands.*;
import com.appworkstips.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;
import java.util.List;

@Ignore("Only used for checking results to a real environment")
public class ServiceCallerRealTest {
    private String otdsTicket;

    @Before
    public void getOTDSTicker() {
        otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }

    @Test
    public void getAllUsers() throws SOAPException {
        GetAllUsers getAllUsers = new GetAllUsers(otdsTicket, "");
        getAllUsers.execute(getAllUsers.buildSoapMessage());

        List<User> users = ResultParser.getInstance().parseToList();
        Assert.assertNotEquals(0, users.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws SOAPException {
        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "120");
        getRandomIntValueMinMax.execute(getRandomIntValueMinMax.buildSoapMessage());

        String result = ResultParser.getInstance().getSoapMessage().getSOAPBody().getTextContent();
        Assert.assertNotEquals("", result);
    }

    @Test
    public void createCategoryEntity() throws SOAPException, XPathExpressionException {
        CreateCategoryEntity createCategoryEntity = new CreateCategoryEntity(otdsTicket, true, "MyName", "MyDescription");
        createCategoryEntity.execute(createCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'CreatecategoryResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertNotEquals("", catId);
    }

    @Test
    public void readCategoryEntity() throws SOAPException, XPathExpressionException {
        ReadCategoryEntity readCategoryEntity = new ReadCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.655361");
        readCategoryEntity.execute(readCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'ReadcategoryResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertNotEquals("", catId);
    }

    @Test
    public void deleteCategoryEntity() throws SOAPException, XPathExpressionException {
        DeleteCategoryEntity deleteCategoryEntity = new DeleteCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.983042");
        deleteCategoryEntity.execute(deleteCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'DeletecategoryResponse']/text()");
        Assert.assertEquals("", catId);
    }

    @Test
    public void getAllCategoryEntity() throws SOAPException, XPathExpressionException {
        GetAllCategoryEntity getAllCategoryEntity = new GetAllCategoryEntity(otdsTicket, 0, 20);
        getAllCategoryEntity.execute(getAllCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'AllCategoriesResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertNotEquals("", catId);
    }
}