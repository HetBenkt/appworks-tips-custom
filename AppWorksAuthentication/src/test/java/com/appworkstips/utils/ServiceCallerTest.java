package com.appworkstips.utils;

import com.appworkstips.models.User;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, ServiceCaller.class, PropertiesUtils.class})
public class ServiceCallerTest extends GeneralSetup {

    @Test
    public void getAllUsers() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_ALL_USERS_SOAP_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        List<User> allUsers = ServiceCaller.getAllUsers(otdsTicket, "");
        Assert.assertNotEquals(0, allUsers.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_RANDOM_INT_SOAP_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String randomIntValueMinMax = ServiceCaller.getRandomIntValueMinMax(otdsTicket, "0", "120");
        Assert.assertNotEquals("", randomIntValueMinMax);
    }
}