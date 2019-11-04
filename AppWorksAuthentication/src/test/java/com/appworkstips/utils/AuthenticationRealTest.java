package com.appworkstips.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Only used for checking results to a real environment")
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