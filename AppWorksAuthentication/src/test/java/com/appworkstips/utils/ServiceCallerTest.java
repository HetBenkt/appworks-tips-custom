package com.appworkstips.utils;

import com.appworkstips.commands.*;
import com.appworkstips.models.User;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, PropertiesUtils.class, ServiceUtils.class, CreateCategoryEntity.class, MessageFactory.class})
public class ServiceCallerTest extends GeneralSetup {
    private String otdsTicket;
    @Mock
    private MessageFactory messageFactory;
    @Mock
    private SOAPException exception;

    @Before
    public void getOTDSTicker() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_OTDS_AUTH_JSON_RESULT);
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
    public void exceptions() throws Exception {
        PowerMockito.mockStatic(MessageFactory.class);
        PowerMockito.when(MessageFactory.newInstance()).thenReturn(messageFactory);
        PowerMockito.when(messageFactory.createMessage()).thenThrow(exception);
        PowerMockito.when(exception.getMessage()).thenReturn("SOAPException");

        CreateCategoryEntity createCategoryEntity = new CreateCategoryEntity(otdsTicket, true, "MyName", "MyDescription");
        SOAPMessage soapMessage = createCategoryEntity.buildSoapMessage();
        Assert.assertNull(soapMessage);

        ReadCategoryEntity readCategoryEntity = new ReadCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.655361");
        soapMessage = readCategoryEntity.buildSoapMessage();
        Assert.assertNull(soapMessage);

        DeleteCategoryEntity deleteCategoryEntity = new DeleteCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.655361");
        soapMessage = deleteCategoryEntity.buildSoapMessage();
        Assert.assertNull(soapMessage);

        GetAllCategoryEntity getAllCategoryEntity = new GetAllCategoryEntity(otdsTicket, 0, 10);
        soapMessage = getAllCategoryEntity.buildSoapMessage();
        Assert.assertNull(soapMessage);

        GetAllUsers getAllUsers = new GetAllUsers(otdsTicket, "");
        soapMessage = getAllUsers.buildSoapMessage();
        Assert.assertNull(soapMessage);

        GetRandomIntValueMinMax getRandomIntValueMinMax = new GetRandomIntValueMinMax(otdsTicket, "0", "10");
        soapMessage = getRandomIntValueMinMax.buildSoapMessage();
        Assert.assertNull(soapMessage);
    }

    @Test
    public void readCategoryEntity() throws SOAPException, XPathExpressionException, IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_READ_CATEGORY_ENTITY_RESULT);

        ReadCategoryEntity readCategoryEntity = new ReadCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.655361");
        readCategoryEntity.execute(readCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'ReadcategoryResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertEquals("655361", catId);
    }

    @Test
    public void deleteCategoryEntity() throws SOAPException, XPathExpressionException, IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_DELETE_CATEGORY_ENTITY_RESULT);

        DeleteCategoryEntity deleteCategoryEntity = new DeleteCategoryEntity(otdsTicket, "0800276907f1a1ea825c64f7cdc2116a.983042");
        deleteCategoryEntity.execute(deleteCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'DeletecategoryResponse']/text()");
        Assert.assertEquals("", catId);
    }

    @Test
    public void getAllCategoryEntity() throws SOAPException, XPathExpressionException, IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_ALL_CATEGORY_ENTITY_RESULT);

        GetAllCategoryEntity getAllCategoryEntity = new GetAllCategoryEntity(otdsTicket, 0, 20);
        getAllCategoryEntity.execute(getAllCategoryEntity.buildSoapMessage());

        String catId = ResultParser.getInstance().getValue("//*[local-name() = 'AllCategoriesResponse']/*[local-name() = 'category']/*[local-name() = 'category-id']/*[local-name() = 'Id']/text()");
        Assert.assertEquals("2", catId);
    }

}