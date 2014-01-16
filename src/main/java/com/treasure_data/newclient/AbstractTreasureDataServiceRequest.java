package com.treasure_data.newclient;

import java.util.HashMap;
import java.util.Map;

import com.treasure_data.newclient.auth.TreasureDataCredentials;

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
}
