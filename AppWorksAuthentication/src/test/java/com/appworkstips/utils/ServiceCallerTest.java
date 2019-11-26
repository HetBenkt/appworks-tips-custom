package com.appworkstips.utils;

import com.appworkstips.commands.GetRandomIntValueMinMax;
import com.appworkstips.models.User;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, ServiceCaller.class, PropertiesUtils.class, ServiceUtils.class})
public class ServiceCallerTest extends GeneralSetup {

    @Test
    public void getAllUsers() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_ALL_USERS_SOAP_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        List<User> allUsers = new ServiceCaller().getAllUsers(otdsTicket, "");
        Assert.assertEquals(1, allUsers.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws IOException, SOAPException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_RANDOM_INT_SOAP_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "120");
        getRandomIntValueMinMax.execute();
        String result = ResultParser.getInstance().getSoapMessage().getSOAPBody().getTextContent();
        Assert.assertEquals("73", result);
    }

    @Test
    public void createCategoryEntity() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_CREATE_CATEGORY_ENTITY_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String catId = new ServiceCaller().createCategoryEntity(otdsTicket, true, "myName", "myDescription");
        Assert.assertNotEquals("", catId);
    }
}