package com.treasure_data.newclient.model;

public class CreateLogTableRequest extends CreateTableRequest {

    public CreateLogTableRequest() {
        type = Table.Type.LOG;
    }
}
