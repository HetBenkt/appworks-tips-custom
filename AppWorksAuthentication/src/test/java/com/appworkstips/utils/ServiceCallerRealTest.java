package com.appworkstips.utils;

import com.appworkstips.commands.CreateCategoryEntity;
import com.appworkstips.commands.GetAllUsers;
import com.appworkstips.commands.GetRandomIntValueMinMax;
import com.appworkstips.models.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import java.util.List;

@Ignore("Only used for checking results to a real environment")
public class ServiceCallerRealTest {

    @Test
    public void getAllUsers() throws SOAPException {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        GetAllUsers getAllUsers = new GetAllUsers(otdsTicket, "");
        getAllUsers.execute(getAllUsers.buildSoapMessage());
        List<User> users = ResultParser.getInstance().parseToList();
        Assert.assertNotEquals(0, users.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws SOAPException {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "120");
        getRandomIntValueMinMax.execute(getRandomIntValueMinMax.buildSoapMessage());
        String result = ResultParser.getInstance().getSoapMessage().getSOAPBody().getTextContent();
        Assert.assertNotEquals("", result);
    }

    @Test
    public void createCategoryEntity() throws SOAPException {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        CreateCategoryEntity createCategoryEntity = new CreateCategoryEntity(otdsTicket, true, "MyName", "MyDescription");
        createCategoryEntity.execute(createCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getSoapMessage().getSOAPBody().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getTextContent(); //xPath = /CreatecategoryResponse/category/category-id/Id
        Assert.assertNotEquals("", catId);
    }
}