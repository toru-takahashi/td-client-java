package com.treasure_data.newclient;

import java.util.Properties;

public class Configuration implements Constants {

    private Protocol protocol = Protocol.HTTP;

    private Properties props;

    public Configuration(Properties props) {
        this.props = props;
    }

    public Properties getProperties() {
        return props;
    }

    public Object getProtocol() {
        return protocol;
    }
}
