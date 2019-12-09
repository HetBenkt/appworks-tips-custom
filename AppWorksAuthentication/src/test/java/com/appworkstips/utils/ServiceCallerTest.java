package com.appworkstips.utils;

import com.appworkstips.commands.CreateCategoryEntity;
import com.appworkstips.commands.GetAllUsers;
import com.appworkstips.commands.GetRandomIntValueMinMax;
import com.appworkstips.commands.ReadCategoryEntity;
import com.appworkstips.models.User;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, PropertiesUtils.class, ServiceUtils.class})
public class ServiceCallerTest extends GeneralSetup {
    private String otdsTicket;

    @Before
    public void getOTDSTicker() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT);
        otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }

    @Test
    public void getAllUsers() throws IOException, SOAPException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_ALL_USERS_SOAP_RESULT);

        GetAllUsers getAllUsers = new GetAllUsers(otdsTicket, "");
        getAllUsers.execute(getAllUsers.buildSoapMessage());

        List<User> users = ResultParser.getInstance().parseToList();
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void getRandomIntValueMinMax() throws IOException, SOAPException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_RANDOM_INT_SOAP_RESULT);

        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "120");
        getRandomIntValueMinMax.execute(getRandomIntValueMinMax.buildSoapMessage());

        String result = ResultParser.getInstance().getSoapMessage().getSOAPBody().getTextContent();
        Assert.assertEquals("73", result);
    }

    @Test
    public void createCategoryEntity() throws IOException, SOAPException, XPathExpressionException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_CREATE_CATEGORY_ENTITY_RESULT);

        CreateCategoryEntity createCategoryEntity = new CreateCategoryEntity(otdsTicket, true, "MyName", "MyDescription");
        createCategoryEntity.execute(createCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'CreatecategoryResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertEquals("983043", catId);
    }

    @Test
    public void readCategoryEntity() throws SOAPException, XPathExpressionException, IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_READ_CATEGORY_ENTITY_RESULT);

        ReadCategoryEntity readCategoryEntity = new ReadCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.655361");
        readCategoryEntity.execute(readCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'ReadcategoryResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertEquals("655361", catId);
    }
}