package com.treasure_data.newclient.auth;

public class DefaultTreasureDataCredentials implements TreasureDataCredentials {
    private String apiKey;

    public DefaultTreasureDataCredentials(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

}
