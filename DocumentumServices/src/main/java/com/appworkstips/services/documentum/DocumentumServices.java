package com.appworkstips.services.documentum;

import com.appworkstips.services.documentum.dao.DocumentumDAO;
import com.appworkstips.services.documentum.utils.PropertiesUtils;
import com.documentum.fc.client.IDfSession;

import java.util.logging.Logger;


public class DocumentumServices {
    private final static Logger LOGGER = Logger.getLogger(DocumentumServices.class.getName());

    DocumentumServices() {
        //Is never called. AppWorks calls it statically DocumentumServices.synchProperties
    }

    public static String synchProperties(String entityPath, String properties) {
        LOGGER.info(String.format("Passing parameters entityPath: %s, properties: %s", entityPath, properties));
        DocumentumDAO documentumDAO = DocumentumDAO.getInstance();

        String username = PropertiesUtils.getProperyValue("username");
        String password = PropertiesUtils.getProperyValue("password");
        String repository = PropertiesUtils.getProperyValue("repository");
        LOGGER.info("setting.properies retrieval is done");

        documentumDAO.makeConnection(username, password, repository);
        IDfSession session = documentumDAO.getSession();

        if(session == null)
            return "No documentum session created";

        if(session.isConnected()) {
            String documentId = documentumDAO.getDocumentId(entityPath);
            documentumDAO.updateDocument(documentId, PropertiesUtils.buildPropsValues(properties));
            documentumDAO.releaseSession();
            return String.format("Document with id %s updated!", documentId);
        }

        return "No document updated";
    }
}
