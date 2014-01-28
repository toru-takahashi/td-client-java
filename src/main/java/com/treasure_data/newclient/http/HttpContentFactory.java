package com.treasure_data.newclient.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.io.ByteArrayBuffer;

import com.treasure_data.newclient.Configuration;
import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;

public class HttpContentFactory {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public ContentExchange createHttpContent(
            Request<?> request, Configuration conf,
            ExecutionContext context) throws TreasureDataClientException {

        ContentExchange exchange = new ContentExchange();

        // configure URL
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
        exchange.setURI(endpoint);

        // configure HTTP method
        if (request.getMethodName().equals(Request.MethodName.GET)) {
            exchange.setMethod(HttpMethods.GET);
        } else if (request.getMethodName().equals(Request.MethodName.PUT)) {
            // TODO FIXME
        } else if (request.getMethodName().equals(Request.MethodName.POST)) {
            // TODO FIXME
        }

        try {
            String encodedParams = HttpUtils.encodeParameters(request);
            byte[] contents = encodedParams.getBytes(DEFAULT_ENCODING);
            exchange.setRequestContent(new ByteArrayBuffer(contents));
        } catch (UnsupportedEncodingException e) {
            throw new TreasureDataClientException(e);
        }

        return exchange;
    }

}
