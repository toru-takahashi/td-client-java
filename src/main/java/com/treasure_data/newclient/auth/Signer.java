package com.treasure_data.newclient.auth;

import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;

public interface Signer {
    <REQ> void sign(Request<REQ> request, com.treasure_data.newclient.auth.TreasureDataCredentials treasureDataCredentials) throws TreasureDataClientException;
}
