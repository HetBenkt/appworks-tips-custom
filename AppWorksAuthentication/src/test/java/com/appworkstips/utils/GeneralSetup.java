package com.appworkstips.utils;

import org.junit.Before;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeneralSetup {
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

    BufferedReader getReader() {
        return reader;
    }
}
