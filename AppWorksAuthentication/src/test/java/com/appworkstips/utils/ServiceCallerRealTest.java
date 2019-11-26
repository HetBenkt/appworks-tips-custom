package com.appworkstips.utils;

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
    public void getAllUsers() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        List<User> allUsers = new ServiceCaller().getAllUsers(otdsTicket, "");
        Assert.assertNotEquals(0, allUsers.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws SOAPException {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "120");
        getRandomIntValueMinMax.execute();
        String result = ResultParser.getInstance().getSoapMessage().getSOAPBody().getTextContent();
        Assert.assertNotEquals("", result);
    }

    @Test
    public void createCategoryEntity() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String catId = new ServiceCaller().createCategoryEntity(otdsTicket, true, "myName", "myDescription");
        Assert.assertNotEquals("", catId);
    }
}