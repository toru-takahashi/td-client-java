package com.treasure_data.newclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Configuration implements Constants {

    private Properties props;

    protected URI endpoint;

    public Configuration(Properties props) {
        this.props = props;
    }

    public Properties getProperties() {
        return props;
    }

    public void setEndpoint() {
        try {
            endpoint = new URI("http://api.treasure-data.com:80/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public URI getEndpoint() {
        if (endpoint == null) {
            setEndpoint();
        }
        return endpoint;
    }
}
