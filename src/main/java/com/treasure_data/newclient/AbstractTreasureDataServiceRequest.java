package com.treasure_data.newclient;

import java.util.HashMap;
import java.util.Map;

import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public abstract class AbstractTreasureDataServiceRequest
        implements TreasureDataServiceRequest {

    private TreasureDataCredentials credentials;

    public Map<String, String> copyPrivateRequestParameters() {
        return new HashMap<String, String>();
    }

    public void setCredentials(TreasureDataCredentials credentials) {
        this.credentials = credentials;
    }

    public TreasureDataCredentials getCredentials() {
        return credentials;
    }

    public abstract void validate() throws TreasureDataClientException;

    public void validateDatabaseName(String databaseName)
            throws TreasureDataClientException {
        if (databaseName == null) {
            throw new TreasureDataClientException("No specified database");
        }
    }

    public void validateTableName(String tableName)
            throws TreasureDataClientException {
        if (tableName == null) {
            throw new TreasureDataClientException("No specified table");
        }
    }

    public abstract String getResourcePath();
}
