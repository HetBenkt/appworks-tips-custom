package com.appworkstips.services.documentum.dao;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.expr.internal.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DocumentumDAO.class, DfClientX.class})
public class DocumentumDAOTest {

    private DocumentumDAO instance;

    @Mock
    private DfClientX clientX;
    @Mock
    private IDfClient client;
    @Mock
    private IDfSessionManager manager;
    @Mock
    private IDfLoginInfo loginInfo;
    @Mock
    private IDfSession session;
    @Mock
    private IDfDocument document;
    @Mock
    private IDfId objectId;

    @Before
    public void setup() throws Exception {
        PowerMockito.whenNew(DfClientX.class).withNoArguments().thenReturn(clientX);
        when(clientX.getLocalClient()).thenReturn(client);
        when(client.newSessionManager()).thenReturn(manager);
        when(clientX.getLoginInfo()).thenReturn(loginInfo);
        when(manager.getSession(anyString())).thenReturn(session);

        instance = DocumentumDAO.getInstance();
    }

    @Test
    public void getInstance() {
        Assert.assertNotNull(instance);
    }

    @Test
    public void makeConnection() {
        Assert.assertNotNull(instance);
        instance.makeConnection("username", "password", "repository");
    }

    @Test
    public void releaseSession() {
        when(session.isConnected()).thenReturn(true);
        when(session.getSessionManager()).thenReturn(manager);

        Assert.assertNotNull(instance);
        instance.makeConnection("username", "password", "repository");
        instance.releaseSession();

        verify(session, times(1)).getSessionManager();
        verify(manager, times(1)).release(session);
    }

    @Test
    public void updateDocument() throws DfException {
        when(session.getObject(any())).thenReturn(document);

        Assert.assertNotNull(instance);
        instance.makeConnection("username", "password", "repository");
        List<Pair<String, String>> propsValues = new ArrayList<>();
        propsValues.add(new Pair<>("key", "value"));
        instance.updateDocument("", propsValues);
    }

    @Test
    public void getDocumentId() throws DfException {
        when(session.getObjectByPath(any())).thenReturn(document);
        when(document.getObjectId()).thenReturn(objectId);
        when(objectId.getId()).thenReturn("");

        Assert.assertNotNull(instance);
        instance.makeConnection("username", "password", "repository");
        String documentId = instance.getDocumentId("");

        Assert.assertNotNull(documentId);
    }

    @Test
    public void getSession() {
        Assert.assertNotNull(instance);
        instance.makeConnection("username", "password", "repository");
        IDfSession session = instance.getSession();
        Assert.assertNotNull(session);
    }
}