package com.treasure_data.newclient.auth;

import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public interface Signer {
    <REQ extends TreasureDataServiceRequest> void sign(Request<REQ> request, com.treasure_data.newclient.auth.TreasureDataCredentials treasureDataCredentials) throws TreasureDataClientException;
}
