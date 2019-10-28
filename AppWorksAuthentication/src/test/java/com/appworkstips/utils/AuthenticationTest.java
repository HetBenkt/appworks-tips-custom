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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Authentication.class, PropertiesUtils.class})
public class AuthenticationTest {
    private String SAMPLE_JSON_RESULT = "{\"passwordExpirationTime\":0,\"resourceID\":\"5dae6e94-f382-47d5-ba5e-289455e72604\",\"ticket\":\"*VER2*ABTle0_50wJHgDpsif1OaFIajNMCSwAQ-OlvHocu1BT0mxjGy4QrEQMgAToAejoCnozPupKyL_4ygcguCVNMwZGLRgZB9TsyY8zSEwhLzQWlklDN-mzYdbU9ywkw8BOKdNTA1PXWuFCuuYzFncgy_9hP5_HUH53_b4HchzVNbVnlTruGGJ3dzWbVaMp-F49mIK2Ryt3Fg5iGy_75hO6AENOXYNEkMngkQu60Zh_Sd-liVmjuERsjCLyV9iQIYiX69Wql_mgDgCQUzLSSp6AS7ELRWCSQDlF1Fb76piS-Qs6GAHroY4DcdIFUxSL5MFXqZGonM86VbwdL0dNk8Cl8_LURqfXS3ojr7nufcszH8o4bQ5j2qO7CCnfs61V6WmXDCFVeA10zBCKpLX3t2QiXTNFW9nElQox_oA58IS6-84LhDnOYAxiGNjjkLNoTkyBV4u8aNslVZ3yQL4K1xs1ofIjsLcQ47co8vZMqeVGuOzogADKFoAob6ZyWe_bNFLR2w3sG3G3EHEQulzPe3GWG-fOsRIADttBNEHY2OOo0AQnBoltcjbXO4YOtqK6JH8k7HlysUufV8tjX6DfA-D9vhj7rPfl4cS93pJhdPuh44CU5L9HRLGTAZh5D-2iBS3sdd_2qwqY9WYy-ol6ntGUi-I2FKkTw_ricLmJUh5Zy3hIkTXLvkqHwOffJgjNa4nDrIWLaTTAzBcFM1i52a1W3vxevPHyeOLC_b3fGcUVTA5vMH8pDFrJ7qYBl5xUxfcvFafgnxAS2zOD34odrs6EtKMizqNOt695es8XamSQ0FBCO1kp3AHKxUVv_AGXT8UZhhed10A-Fx9WahJ-KRYzaTKaG99pYMhHDhdJyjIEcA4dTJzFeB7vgRqE7Ag7_58I4VXMr_mq_5XJKxD5nbDnXvk9esQNW2V68k6eONhKyPH64S64liBjPBJ1zVhMN1d3dUz7cub7QgpxcHFE3VLQezYQX6vReI0rv6_B4V2rrXEZDB9Vxysb9rMwtbGtS6Kt2X3a-RSt5LILdD58Pq9le2lW7Oek-cXZ-Im4q5LBXasbz72_K5oEAeQky_stL5UHLwTEEoqnnsmJDAA9iIIlkIuRgfnB7eaav6Z0*\",\"failureReason\":null,\"continuationData\":null,\"userId\":\"otdsdev@AppWorks Platform Partition\",\"continuation\":false,\"continuationContext\":null,\"token\":\"6F7464735F73657373696F6E5F6B6579\"}";

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
        PowerMockito.when(reader.readLine()).thenReturn(SAMPLE_JSON_RESULT);
    }

    @Test
    public void getOTDSTicket() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);
    }

    @Test
    public void getSAMLAssertionArtifact() {
        String otdsTicket = Authentication.getOTDSTicket();
        Assert.assertNotEquals("", otdsTicket);

        Authentication.getSAMLAssertionArtifact(otdsTicket);
    }
}