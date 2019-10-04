package com.appworkstips.services.documentum.dao;

import com.documentum.fc.expr.internal.Pair;

import java.util.List;

public interface IDocumentumDAO {
    void makeConnection(String userName, String password, String repositoryName);

    void releaseSession();

    void updateDocument(String documentId, List<Pair<String, String>> propsValues);
}
