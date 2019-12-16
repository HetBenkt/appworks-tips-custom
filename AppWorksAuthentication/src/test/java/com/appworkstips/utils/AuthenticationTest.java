package com.appworkstips.utils;

import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, PropertiesUtils.class})
public class AuthenticationTest extends GeneralSetup {
    @Mock
    private ConnectException connectException;
    @Mock
    private MalformedURLException malformedURLException;
    @Mock
    private IOException ioException;

    @Test
    public void getOTDSTicket() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_OTDS_AUTH_JSON_RESULT);

        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }

    @Test
    public void getOTDSTicketConnectionTimeOut() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_OTDS_AUTH_JSON_RESULT);
        PowerMockito.when(getConnection().getOutputStream()).thenThrow(connectException);
        PowerMockito.when(connectException.getMessage()).thenReturn("Connection timed out: connect");

        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertEquals("", otdsTicket);
    }

    @Test
    public void getOTDSTicketConnectionIsNull() throws IOException {
        PowerMockito.when(getUrl().openConnection()).thenThrow(ioException);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertEquals("", otdsTicket);
    }

    @Test
    public void createConnectionIsNull() throws Exception {
        PowerMockito.when(getUrl().openConnection()).thenThrow(ioException);
        HttpURLConnection connection = Authentication.createConnection("", "", "");
        Assert.assertNull(connection);
    }
}