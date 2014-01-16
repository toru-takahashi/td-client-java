package com.treasure_data.newclient;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.treasure_data.newclient.http.HttpMethodName;

public class DefaultRequest<T> implements Request<T> {

    private String resourcePath;
    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();
    private URI endpoint;

    private final TreasureDataServiceRequest originalRequest;

    private HttpMethodName httpMethod = HttpMethodName.POST;
    private InputStream content;

    public DefaultRequest(TreasureDataServiceRequest originalRequest) {
        this.originalRequest = originalRequest;
    }

    public TreasureDataServiceRequest getOriginalRequest() {
        return originalRequest;
    }

    @Override
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    @Override
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    @Override
    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    @Override
    public Request<T> withParameter(String name, String value) {
        addParameter(name, value);
        return this;
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    @Override
    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public URI getEndpoint() {
        return endpoint;
    }

    @Override
    public void setHttpMethod(HttpMethodName httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public HttpMethodName getHttpMethod() {
        return httpMethod;
    }

    @Override
    public InputStream getContent() {
        return content;
    }

    @Override
    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sbuf = new StringBuilder();

        sbuf.append(getHttpMethod().toString() + " ");
        sbuf.append(getEndpoint().toString() + " ");

        sbuf.append("/" + (getResourcePath() != null ? getResourcePath() : "")
                + " ");

        if (!getParameters().isEmpty()) {
            sbuf.append("Parameters: (");
            for (String key : getParameters().keySet()) {
                String value = getParameters().get(key);
                sbuf.append(key + ": " + value + ", ");
            }
            sbuf.append(") ");
        }

        if (!getHeaders().isEmpty()) {
            sbuf.append("Headers: (");
            for (String key : getHeaders().keySet()) {
                String value = getHeaders().get(key);
                sbuf.append(key + ": " + value + ", ");
            }
            sbuf.append(") ");
        }

        return sbuf.toString();
    }
}
