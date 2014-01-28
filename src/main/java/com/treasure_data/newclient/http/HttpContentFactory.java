package com.treasure_data.newclient.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;

import com.treasure_data.newclient.Configuration;
import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public class HttpContentFactory {

    public static class TreasureDataContentExchange extends ContentExchange {

        protected Buffer buf;

        public TreasureDataContentExchange() {
            super(false); // cache fields is inactive
        }

        public InputStream getContent() throws IOException {
            if (buf != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                buf.writeTo(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
            return null;
        }

        @Override
        protected void onResponseStatus(Buffer version, int status, Buffer reason)
                throws IOException {
            super.onResponseStatus(version, status, reason);
        }

        @Override
        protected void onResponseHeader(Buffer name, Buffer value)
                throws IOException {
            super.onResponseHeader(name, value);
        }

        @Override
        protected synchronized void onResponseContent(Buffer content) throws IOException {
            buf = content;
        }

        @Override
        protected void onConnectionFailed(Throwable t) {
            t.printStackTrace();
        }
    }

    public <REQ extends TreasureDataServiceRequest> ContentExchange createHttpContent(
            Request<REQ> request, Configuration conf)
                    throws TreasureDataClientException {
        ContentExchange exchange = new TreasureDataContentExchange();

        // configure headers
        for (Map.Entry<String, String> e : request.getHeaders().entrySet()) {
            exchange.addRequestHeader(e.getKey(), e.getValue());
        }

        // configure path
        String uri = request.getEndpoint();
        if (request.getResourcePath() != null && request.getResourcePath().length() > 0) {
            if (!request.getResourcePath().startsWith("/")) {
                uri += "/";
            }
            uri += request.getResourcePath();
        } else if (!uri.endsWith("/")) {
            uri += "/";
        }
        try {
            exchange.setURI(new URI(uri));
        } catch (URISyntaxException e) {
            throw new TreasureDataClientException(e);
        }

        // configure HTTP method
        if (request.getMethodName().equals(Request.MethodName.GET)) {
            exchange.setMethod(HttpMethods.GET);
        } else if (request.getMethodName().equals(Request.MethodName.PUT)) {
            // TODO FIXME
        } else if (request.getMethodName().equals(Request.MethodName.POST)) {
            // TODO FIXME
        }

        // configure parameters
        try {
            String encodedParams = HttpUtils.encodeParameters(request);
            byte[] contents = encodedParams.getBytes("UTF-8");
            exchange.setRequestContent(new ByteArrayBuffer(contents));
        } catch (UnsupportedEncodingException e) {
            throw new TreasureDataClientException(e);
        }

        return exchange;
    }
}
