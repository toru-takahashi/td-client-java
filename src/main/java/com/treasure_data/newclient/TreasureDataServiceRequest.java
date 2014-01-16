package com.treasure_data.newclient;

import java.util.Map;

import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.http.HttpMethodName;

public interface TreasureDataServiceRequest {

    Map<String, String> copyPrivateRequestParameters();

    void setCredentials(TreasureDataCredentials credentials);
    TreasureDataCredentials getCredentials();

    void validate() throws TreasureDataClientException;
    String getResourcePath();
    HttpMethodName getHttpMethodName();
}
