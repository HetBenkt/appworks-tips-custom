package com.appworkstips.utils;

import com.appworkstips.services.documentum.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, PropertiesUtils.class})
public class AuthenticationTest extends GeneralSetup {

    @Test
    public void getOTDSTicket() throws IOException {
        PowerMockito.when(getReader().readLine()).thenReturn(IConstants.SAMPLE_JSON_RESULT);
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }
}