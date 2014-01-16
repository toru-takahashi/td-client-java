package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.CoreProtocolPNames;

import com.treasure_data.newclient.Configuration;
import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;

public class HttpRequestFactory {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public HttpRequestBase createHttpRequest(
            Request<?> request, Configuration conf,
            HttpEntity previousEntity, ExecutionContext context) throws TreasureDataClientException {
        URI endpoint = request.getEndpoint();
        String uri = endpoint.toString();
        if (request.getResourcePath() != null && request.getResourcePath().length() > 0) {
            if (!request.getResourcePath().startsWith("/")) {
                uri += "/";
            }
            uri += request.getResourcePath();
        } else if (!uri.endsWith("/")) {
            uri += "/";
        }

        String encodedParams = HttpUtils.encodeParameters(request);

        boolean requestHasNoPayload = request.getContent() != null;
        boolean requestIsPost = request.getHttpMethod() == HttpMethodName.POST;
        boolean putParamsInUri = !requestIsPost || requestHasNoPayload;
        if (encodedParams != null && putParamsInUri) {
            uri += "?" + encodedParams;
        }

        HttpRequestBase httpRequest;
        if (request.getHttpMethod() == HttpMethodName.POST) {
            HttpPost postMethod = new HttpPost(uri);

            if (request.getContent() == null && encodedParams != null) {
                postMethod.setEntity(newStringEntity(encodedParams));
            } else {
                postMethod.setEntity(new RepeatableInputStreamRequestEntity(request));
            }
            httpRequest = postMethod;
        } else if (request.getHttpMethod() == HttpMethodName.PUT) {
            HttpPut putMethod = new HttpPut(uri);
            httpRequest = putMethod;

            putMethod.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, true);

            if (previousEntity != null) {
                putMethod.setEntity(previousEntity);
            } else if (request.getContent() != null) {
                HttpEntity entity = new RepeatableInputStreamRequestEntity(request);
                if (request.getHeaders().get("Content-Length") == null) {
                    entity = newBufferedHttpEntity(entity);
                }
                putMethod.setEntity(entity);
            }
        } else if (request.getHttpMethod() == HttpMethodName.GET) {
            httpRequest = new HttpGet(uri);
        } else if (request.getHttpMethod() == HttpMethodName.DELETE) {
            httpRequest = new HttpDelete(uri);
        } else if (request.getHttpMethod() == HttpMethodName.HEAD) {
            httpRequest = new HttpHead(uri);
        } else {
            throw new TreasureDataClientException("Unknown HTTP method name: " + request.getHttpMethod());
        }

        configureHeaders(httpRequest, request, context, conf);

        return httpRequest;
    }

    private void configureHeaders(HttpRequestBase httpRequest,
            Request<?> request, ExecutionContext context, Configuration conf) {
        /*
         * Apache HttpClient omits the port number in the Host header (even if
         * we explicitly specify it) if it's the default port for the protocol
         * in use. To ensure that we use the same Host header in the request and
         * in the calculated string to sign (even if Apache HttpClient changed
         * and started honoring our explicit host with endpoint), we follow this
         * same behavior here and in the QueryString signer.
         */
        URI endpoint = request.getEndpoint();
        String hostHeader = endpoint.getHost();
        if (HttpUtils.isUsingNonDefaultPort(endpoint)) {
            hostHeader += ":" + endpoint.getPort();
        }
        httpRequest.addHeader("Host", hostHeader);

        // Copy over any other headers already in our request
        for (Entry<String, String> entry : request.getHeaders().entrySet()) {
            /*
             * HttpClient4 fills in the Content-Length header and complains if
             * it's already present, so we skip it here. We also skip the Host
             * header to avoid sending it twice, which will interfere with some
             * signing schemes.
             */
            if (entry.getKey().equalsIgnoreCase("Content-Length") || entry.getKey().equalsIgnoreCase("Host")) continue;

            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }

        /* Set content type and encoding */
        if (httpRequest.getHeaders("Content-Type") == null || httpRequest.getHeaders("Content-Type").length == 0) {
            httpRequest.addHeader("Content-Type",
                    "application/x-www-form-urlencoded; " +
                    "charset=" + DEFAULT_ENCODING.toLowerCase());
        }

    }

    private HttpEntity newStringEntity(String s) throws TreasureDataClientException {
        try {
            return new StringEntity(s);
        } catch (UnsupportedEncodingException e) {
            throw new TreasureDataClientException("Unable to create HTTP entity: " + e.getMessage(), e);
        }
    }

    private HttpEntity newBufferedHttpEntity(HttpEntity entity) throws TreasureDataClientException {
        try {
            return new BufferedHttpEntity(entity);
        } catch (IOException e) {
            throw new TreasureDataClientException("Unable to create HTTP entity: " + e.getMessage(), e);
        }
    }

}
