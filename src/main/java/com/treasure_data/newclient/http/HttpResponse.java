package com.treasure_data.newclient.http;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;

import com.treasure_data.newclient.Request;

public class HttpResponse {
    private final Request<?> request;
    private final HttpRequestBase httpRequest;

    private String statusText;
    private int statusCode;
    private InputStream content;
    private Map<String, String> headers = new HashMap<String, String>();

    public HttpResponse(Request<?> request,
            HttpRequestBase httpRequest) {
        this.request = request;
        this.httpRequest = httpRequest;
    }

    public Request<?> getRequest() {
        return request;
    }

    public HttpRequestBase getHttpRequest() {
        return httpRequest;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public InputStream getContent() {
        return content;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
