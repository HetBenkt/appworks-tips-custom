package com.appworkstips;

import com.appworkstips.utils.ServiceUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GenericService.class, ServiceUtils.class})
public class GenericServiceTest {
    @Mock
    private SOAPMessage soapMessage;
    @Mock
    private SOAPException soapException;

    @Test
    public void executeWithNull() {
        GenericService genericService = new GenericService();
        genericService.execute(null);
    }

    @Test
    public void executeWithException() throws IOException, SOAPException {
        PowerMockito.mockStatic(ServiceUtils.class);
        PowerMockito.when(ServiceUtils.callService(any(), any())).thenThrow(soapException);
        PowerMockito.when(soapException.getMessage()).thenReturn("SoapException");

        GenericService genericService = new GenericService();
        genericService.execute(soapMessage);
    }

}