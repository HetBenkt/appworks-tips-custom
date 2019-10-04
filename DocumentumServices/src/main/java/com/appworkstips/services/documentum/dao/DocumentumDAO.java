package com.appworkstips.services.documentum.dao;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.expr.internal.Pair;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentumDAO implements IDocumentumDAO {
    private static final Logger LOGGER = Logger.getLogger(DocumentumDAO.class.getName());

    private IDfClientX clientX = new DfClientX();

    private IDfSession session;
    private static DocumentumDAO instance;

    public static DocumentumDAO getInstance() {
        if (instance == null)
            instance = new DocumentumDAO();
        return instance;
    }

    public void makeConnection(String username, String password, String repository) {
        LOGGER.info(String.format("Making connection with: %s, %s, %s", username, password, repository));
        try {
            IDfClient client = clientX.getLocalClient();
            IDfSessionManager manager = client.newSessionManager();
            IDfLoginInfo loginInfo = clientX.getLoginInfo();
            loginInfo.setUser(username);
            loginInfo.setPassword(password);
            loginInfo.setDomain(null);
            manager.setIdentity(repository, loginInfo);
            session = manager.getSession(repository);
        } catch (DfException dfe) {
            LOGGER.log(Level.SEVERE, dfe.getMessage(), dfe);
        }
    }

    public void releaseSession() {
        if(session.isConnected()) {
            session.getSessionManager().release(session);
        }
    }

    public void updateDocument(String documentId, List<Pair<String, String>> propsValues) {
        try {
            IDfDocument document = (IDfDocument) session.getObject(new DfId(documentId));
            for(Pair<String, String> propValue: propsValues) {
                document.setString(propValue.first(), propValue.second());
            }
            document.save();
        } catch (DfException dfe) {
            LOGGER.log(Level.SEVERE, dfe.getMessage(), dfe);
        }
    }

    public String getDocumentId(String path) {
        try {
            IDfDocument document = (IDfDocument) session.getObjectByPath(path);
            return document.getObjectId().getId();
        } catch (DfException dfe) {
            LOGGER.log(Level.SEVERE, dfe.getMessage(), dfe);
        }
        return null;
    }

    public IDfSession getSession() {
        return session;
    }
}
