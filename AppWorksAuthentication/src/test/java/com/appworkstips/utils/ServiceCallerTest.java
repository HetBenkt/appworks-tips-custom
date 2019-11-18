package com.appworkstips.utils;

import com.appworkstips.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServiceCallerTest {

    @Test
    public void getAllUsers() {
        String otdsTicket = Authentication.getOTDSTicket();
        List<User> allUsers = ServiceCaller.getAllUsers(otdsTicket, "");
        Assert.assertNotEquals(0, allUsers.size());
    }
}