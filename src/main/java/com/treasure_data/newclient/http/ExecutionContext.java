package com.treasure_data.newclient.http;

import com.treasure_data.newclient.auth.Signer;
import com.treasure_data.newclient.auth.TreasureDataCredentials;

public class ExecutionContext {

    private TreasureDataCredentials credentials;
    private Signer signer;

    public ExecutionContext() {
    }

    public TreasureDataCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(TreasureDataCredentials credentials) {
        this.credentials = credentials;
    }

    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    public Signer getSinger() {
        return signer;
    }
}
