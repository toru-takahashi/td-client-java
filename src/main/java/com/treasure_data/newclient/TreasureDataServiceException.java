package com.treasure_data.newclient;

public class TreasureDataServiceException extends TreasureDataClientException {
    private String errorCode;
    private int statusCode;

    public TreasureDataServiceException(String message) {
        super(message);
    }

    public TreasureDataServiceException(String message, Exception cause) {
        super(message, cause);
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
