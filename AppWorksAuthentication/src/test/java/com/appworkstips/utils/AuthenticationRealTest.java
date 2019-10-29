package com.appworkstips.utils;

import org.junit.Assert;
import org.junit.Test;

public class AuthenticationRealTest {

    @Test
    public void getOTDSTicket() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }

    @Test
    public void getRandomIntValueMinMax() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String randomIntValueMinMax = Authentication.getRandomIntValueMinMax(otdsTicket, "0", "120");
        Assert.assertNotEquals("", randomIntValueMinMax);
    }
}