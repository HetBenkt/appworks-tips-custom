package com.appworkstips.services.documentum;

import com.appworkstips.services.documentum.dao.DocumentumDAO;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import com.documentum.fc.client.IDfSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DocumentumDAO.class, DocumentumServices.class, PropertiesUtils.class})
public class DocumentumServicesTest {
    private static final String OBJECT_ID = "0911882273365241";
    @Mock
    private DocumentumDAO documentumDAO;
    @Mock
    private IDfSession session;

    @Test
    public void synchProperties() {
        PowerMockito.mockStatic(DocumentumDAO.class);
        PowerMockito.mockStatic(PropertiesUtils.class);
        when(DocumentumDAO.getInstance()).thenReturn(documentumDAO);
        when(documentumDAO.getSession()).thenReturn(session);
        when(session.isConnected()).thenReturn(true);
        when(documentumDAO.getDocumentId(anyString())).thenReturn(OBJECT_ID);
        String result = DocumentumServices.synchProperties("", "");
        Assert.assertEquals(String.format("Document with id %s updated!", OBJECT_ID), result);
    }
}
