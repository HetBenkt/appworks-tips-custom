package com.appworkstips.utils;

import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, ServiceCaller.class, PropertiesUtils.class})
public class ServiceCallerTest {
    @Mock
    private URL url;
    @Mock
    private HttpURLConnection connection;
    @Mock
    private OutputStream outputStream;
    @Mock
    private InputStream inputStream;
    @Mock
    private InputStreamReader inputStreamReader;
    @Mock
    private BufferedReader reader;

    @Before
    public void setup() throws Exception {
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);
        PowerMockito.when(url.openConnection()).thenReturn(connection);
        PowerMockito.when(connection.getOutputStream()).thenReturn(outputStream);
        PowerMockito.when(connection.getInputStream()).thenReturn(inputStream);
        PowerMockito.whenNew(InputStreamReader.class).withAnyArguments().thenReturn(inputStreamReader);
        PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(reader);
    }

    @Test
    public void getAllUsers() {
        //TODO
    }

    @Test
    public void getRandomIntValueMinMax() throws IOException {
        PowerMockito.when(reader.readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT, IConstants.SAMPLE_SOAP_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        String randomIntValueMinMax = ServiceCaller.getRandomIntValueMinMax(otdsTicket, "0", "120");
        Assert.assertNotEquals("", randomIntValueMinMax);
    }
}