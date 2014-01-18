package com.treasure_data.newclient.model;

public class CreateItemTableRequest extends CreateTableRequest {

    public CreateItemTableRequest() {
        type = Table.Type.ITEM;
    }
}
