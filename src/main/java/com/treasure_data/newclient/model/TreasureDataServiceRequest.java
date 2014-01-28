package com.treasure_data.newclient.model;

import com.treasure_data.newclient.TreasureDataClientException;

public interface TreasureDataServiceRequest {

    void validate() throws TreasureDataClientException;

    String getResourcePath();
}
