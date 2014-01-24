package com.treasure_data.newclient.http;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.treasure_data.newclient.Configuration;
import com.treasure_data.newclient.TreasureDataClientException;

public class HttpClientFactory {

    public HttpClient createHttpClient(Configuration conf) throws TreasureDataClientException {
        HttpClient httpClient = new HttpClient();

        // manage thread pooling
        httpClient.setThreadPool(new QueuedThreadPool(10)); // TODO FIXME 10 should be configured

        //http.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        //http.setConnectorType(HttpClient.CONNECTOR_SOCKET);
        //http.setConnectTimeout(10000);
        //http.setTimeout(60000);
        //http.setIdleTimeout(1000);
        //http.setMaxConnectionsPerAddress(6);
        
        return httpClient;
    }
}
