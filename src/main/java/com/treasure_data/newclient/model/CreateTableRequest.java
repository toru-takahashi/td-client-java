package com.treasure_data.newclient.model;

import com.treasure_data.newclient.AbstractTreasureDataServiceRequest;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.http.ResourcePath;

class CreateTableRequest extends AbstractTreasureDataServiceRequest {

    protected String databaseName;
    protected String tableName;
    protected Table.Type type;

    CreateTableRequest() {
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void validate() throws TreasureDataClientException {
        validateDatabaseName(databaseName);
        validateTableName(tableName);
    }
    @Override
    public String getResourcePath() {
        return String.format(ResourcePath.V3_TABLE_CREATE,
                databaseName, tableName, type.type());
    }
}
