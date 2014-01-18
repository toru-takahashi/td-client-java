package com.treasure_data.newclient.model;

import com.treasure_data.newclient.AbstractTreasureDataServiceRequest;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.http.ResourcePath;

public class GetServerStatusRequest extends AbstractTreasureDataServiceRequest{

    @Override
    public void validate() throws TreasureDataClientException {
    }

    @Override
    public String getResourcePath() {
        return ResourcePath.V3_SERVER_STATUS;
    }

}
