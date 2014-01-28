package com.treasure_data.newclient;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import com.treasure_data.client.Constants;
import com.treasure_data.newclient.auth.DefaultTreasureDataCredentials;
import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.http.ExecutionContext;
import com.treasure_data.newclient.http.TreasureDataHttpClient;

public abstract class AbstractTreasureDataClient implements Closeable {

    private static final Logger LOG = Logger.getLogger(AbstractTreasureDataClient.class.getName());

    protected TreasureDataCredentials credentials;
    protected Configuration conf;
    protected TreasureDataHttpClient client;

    public AbstractTreasureDataClient(Configuration conf)
            throws TreasureDataClientException {
        this.conf = conf;
        this.client = new TreasureDataHttpClient(conf);
    }

    protected ExecutionContext createExecutionContext() {
        ExecutionContext executionContext = new ExecutionContext();
        return executionContext;
    }

    protected TreasureDataCredentials getCredentials() {
        if (credentials != null) {
            return credentials;
        }

        final String apiKey = conf.getProperties().getProperty(Constants.TD_API_KEY);
        // TODO FIXME null check of api key
        credentials = new DefaultTreasureDataCredentials(apiKey);
        return credentials;
    }

    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}
