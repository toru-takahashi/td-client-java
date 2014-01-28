package com.treasure_data.newclient;

import java.util.Properties;

public class Configuration implements Constants {

    protected Properties props;

    protected String endpoint;

    public Configuration(Properties props) {
        this.props = props;
    }

    public Properties getProperties() {
        return props;
    }

    public void setEndpoint() {
        // TODO FIXME this constant value should be declared in Constants class
        endpoint = "http://api.treasure-data.com:80/";
    }

    public String getEndpoint() {
        if (endpoint == null) {
            setEndpoint();
        }
        return endpoint;
    }
}
