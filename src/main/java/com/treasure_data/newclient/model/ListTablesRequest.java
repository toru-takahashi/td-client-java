package com.treasure_data.newclient.model;

import com.treasure_data.newclient.AbstractTreasureDataServiceRequest;
import com.treasure_data.newclient.TreasureDataClientException;

public class ListTablesRequest extends AbstractTreasureDataServiceRequest {

    protected String databaseName;

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public void validate() throws TreasureDataClientException {
        if (databaseName == null) {
            throw new TreasureDataClientException("Not specified database name");
        }
    }
}
