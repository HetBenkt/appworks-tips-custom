package com.appworkstips.utils;

import com.appworkstips.models.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

@Ignore("Only used for checking results to a real environment")
public class ServiceCallerRealTest {

    @Test
    public void getAllUsers() {
        String otdsTicket = Authentication.getOTDSTicket();
        List<User> allUsers = ServiceCaller.getAllUsers(otdsTicket, "");
        Assert.assertNotEquals(0, allUsers.size());
    }

    @Test
    public void getRandomIntValueMinMax() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String randomIntValueMinMax = ServiceCaller.getRandomIntValueMinMax(otdsTicket, "0", "120");
        Assert.assertNotEquals("", randomIntValueMinMax);
    }

}