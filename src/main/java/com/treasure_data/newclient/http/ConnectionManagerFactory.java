package com.treasure_data.newclient.http;

import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import com.treasure_data.newclient.Configuration;

public class ConnectionManagerFactory {

    public static ThreadSafeClientConnManager createThreadSafeClientConnManager(
            Configuration conf,
            HttpParams httpClientParams) {
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
//        connectionManager.setDefaultMaxPerRoute(conf.getMaxConnections());
//        connectionManager.setMaxTotal(conf.getMaxConnections());

        return connectionManager;
    }
}
