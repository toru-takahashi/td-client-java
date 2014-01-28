package com.treasure_data.newclient;

import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public abstract class AbstractTreasureDataServiceRequest
        implements TreasureDataServiceRequest {

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
