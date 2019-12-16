package com.appworkstips.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceUtils.class})
public class ServiceUtilsTest {

    @Mock
    private SOAPEnvelope soapEnvelope;
    @Mock
    private SOAPHeader soapHeader;
    @Mock
    private SOAPHeaderElement otAuthenticationElement;
    @Mock
    private SOAPElement authenticationTokenElement;
    @Mock
    private HttpURLConnection connection;
    @Mock
    private SOAPMessage soapMessage;
    @Mock
    private ByteArrayOutputStream out;
    @Mock
    private OutputStream outputStream;
    @Mock
    private InputStreamReader inputStreamReader;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private ByteArrayInputStream is;

    @Test
    public void addAuthenticationHeader() throws SOAPException {
        PowerMockito.when(soapEnvelope.getHeader()).thenReturn(soapHeader);
        PowerMockito.when(soapHeader.addHeaderElement(new QName("urn:api.bpm.opentext.com", "OTAuthentication"))).thenReturn(otAuthenticationElement);
        PowerMockito.when(otAuthenticationElement.addChildElement("AuthenticationToken")).thenReturn(authenticationTokenElement);

        String token = "";
        ServiceUtils.addAuthenticationHeader(soapEnvelope, token);
        verify(authenticationTokenElement, times(1)).setTextContent(token);
    }

    @Test
    public void callService() throws Exception {
        PowerMockito.whenNew(ByteArrayOutputStream.class).withNoArguments().thenReturn(out);
        PowerMockito.when(out.toByteArray()).thenReturn("hello world".getBytes());
        PowerMockito.when(connection.getOutputStream()).thenReturn(outputStream);
        PowerMockito.whenNew(InputStreamReader.class).withAnyArguments().thenReturn(inputStreamReader);
        PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(bufferedReader);
        PowerMockito.when(bufferedReader.readLine()).thenReturn("hello world");
        PowerMockito.whenNew(ByteArrayInputStream.class).withAnyArguments().thenReturn(is);

        SOAPMessage soapMessageResult = ServiceUtils.callService(connection, soapMessage);
        Assert.assertNotNull(soapMessageResult);

    }
}