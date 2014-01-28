package com.treasure_data.newclient;

public interface Constants {
    String TD_ENV_API_KEY = "TREASURE_DATA_API_KEY";
    String TD_API_KEY = "td.api.key";

    String TD_INTERNAL_KEY = "td.api.internalkey";
    String TD_INTERNAL_KEY_ID = "td.api.internalkeyid";

    String TD_ENV_API_SERVER = "TD_API_SERVER";
    String TD_API_SERVER_HOST = "td.api.server.host";
    String TD_API_SERVER_HOST_DEFAULTVALUE = "api.treasure-data.com";

    String TD_API_SERVER_PORT = "td.api.server.port";
    String TD_API_SERVER_PORT_DEFAULTVALUE = "80";

    String TD_AUTO_CREATE_TABLE = "td.create.table.auto";
    String TD_AUTO_CREATE_TABLE_DEFAULTVALUE = "false";

    String TD_CLIENT_CONNECT_TIMEOUT = "td.client.connect.timeout";
    String TD_CLIENT_CONNECT_TIMEOUT_DEFAULTVALUE = "" + 60 * 1000; // millis

    String TD_CLIENT_GETMETHOD_READ_TIMEOUT = "td.client.getmethod.read.timeout";
    String TD_CLIENT_GETMETHOD_READ_TIMEOUT_DEFAULTVALUE = "" + 600 * 1000; // millis

    String TD_CLIENT_PUTMETHOD_READ_TIMEOUT = "td.client.putmethod.read.timeout";
    String TD_CLIENT_PUTMETHOD_READ_TIMEOUT_DEFAULTVALUE = "" + 600 * 1000; // millis

    String TD_CLIENT_POSTMETHOD_READ_TIMEOUT = "td.client.postmethod.read.timeout";
    String TD_CLIENT_POSTMETHOD_READ_TIMEOUT_DEFAULTVALUE = "" + 600 * 1000; // millis

    String TD_CLIENT_RETRY_COUNT = "td.client.retry.count";
    String TD_CLIENT_RETRY_COUNT_DEFAULTVALUE = "8";

    String TD_CLIENT_RETRY_WAIT_TIME = "td.client.retry.wait.time";
    String TD_CLIENT_RETRY_WAIT_TIME_DEFAULTVALUE = "1000";

    // max exponential back-off time before retrying a request
    int MAX_BACKOFF_IN_MILLISECONDS = 20 * 1000; // millis

    int HTTP_OK = 200;
}
