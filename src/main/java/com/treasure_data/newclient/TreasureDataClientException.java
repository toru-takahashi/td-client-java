package com.treasure_data.newclient;

public class TreasureDataClientException extends Exception {

    public TreasureDataClientException(Exception cause) {
        super(cause);
    }

    public TreasureDataClientException(String message) {
        super(message);
    }

    public TreasureDataClientException(String message, Exception cause) {
        super(message, cause);
    }

}
