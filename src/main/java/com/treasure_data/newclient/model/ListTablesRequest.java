package com.treasure_data.newclient.model;

import com.treasure_data.newclient.AbstractTreasureDataServiceRequest;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.http.HttpMethodName;
import com.treasure_data.newclient.http.ResourcePath;

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

    @Override
    public String getResourcePath() {
        return String.format(ResourcePath.V3_TABLES_LIST, databaseName);
    }

    @Override
    public HttpMethodName getHttpMethodName() {
        return HttpMethodName.GET;
    }
}
