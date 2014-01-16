package com.treasure_data.newclient.http;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.treasure_data.newclient.Configuration;
import com.treasure_data.newclient.TreasureDataClientException;

public class HttpClientFactory {

    public HttpClient createHttpClient(Configuration conf) throws TreasureDataClientException {
//        /* Form User-Agent information */
//        String userAgent = config.getUserAgent();
//        if (!(userAgent.equals(ClientConfiguration.DEFAULT_USER_AGENT))) {
//            userAgent += ", " + ClientConfiguration.DEFAULT_USER_AGENT;
//        }

        /* Set HTTP client parameters */
        HttpParams httpClientParams = new BasicHttpParams();
//        HttpProtocolParams.setUserAgent(httpClientParams, userAgent);
//        HttpConnectionParams.setConnectionTimeout(httpClientParams, config.getConnectionTimeout());
//        HttpConnectionParams.setSoTimeout(httpClientParams, config.getSocketTimeout());
//        HttpConnectionParams.setStaleCheckingEnabled(httpClientParams, true);
//        HttpConnectionParams.setTcpNoDelay(httpClientParams, true);

//        int socketSendBufferSizeHint = config.getSocketBufferSizeHints()[0];
//        int socketReceiveBufferSizeHint = config.getSocketBufferSizeHints()[1];
//        if (socketSendBufferSizeHint > 0 || socketReceiveBufferSizeHint > 0) {
//            HttpConnectionParams.setSocketBufferSize(httpClientParams,
//                    Math.max(socketSendBufferSizeHint, socketReceiveBufferSizeHint));
//        }

        /* Set connection manager */
        ThreadSafeClientConnManager connectionManager = ConnectionManagerFactory.createThreadSafeClientConnManager(conf, httpClientParams);
        DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager, httpClientParams);

        try {
            Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());

            SSLSocketFactory sf = new SSLSocketFactory(
                    SSLContext.getDefault(),
                    SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", 443, sf);

            SchemeRegistry sr = connectionManager.getSchemeRegistry();
            sr.register(http);
            sr.register(https);
        } catch (NoSuchAlgorithmException e) {
            throw new TreasureDataClientException("Unable to access default SSL context");
        }

//        /* Set proxy if configured */
//        String proxyHost = config.getProxyHost();
//        int proxyPort = config.getProxyPort();
//        if (proxyHost != null && proxyPort > 0) {
//            LOG.info("Configuring Proxy. Proxy Host: " + proxyHost + " " + "Proxy Port: " + proxyPort);
//            HttpHost proxyHttpHost = new HttpHost(proxyHost, proxyPort);
//            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
//
//            String proxyUsername    = config.getProxyUsername();
//            String proxyPassword    = config.getProxyPassword();
//            String proxyDomain      = config.getProxyDomain();
//            String proxyWorkstation = config.getProxyWorkstation();
//
//            if (proxyUsername != null && proxyPassword != null) {
//                httpClient.getCredentialsProvider().setCredentials(
//                        new AuthScope(proxyHost, proxyPort),
//                        new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
//            }
//        }

        return httpClient;
    }
}
