package com.treasure_data.newclient;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import com.treasure_data.newclient.http.HttpMethodName;

public interface Request<T> {

    TreasureDataServiceRequest getOriginalRequest();

    void addHeader(String name, String value);
    Map<String, String> getHeaders();
    void setHeaders(Map<String, String> headers);

    void setResourcePath(String path);
    String getResourcePath();

    void addParameter(String name, String value);
    Request<T> withParameter(String name, String value);
    Map<String, String> getParameters();
    void setParameters(Map<String, String> parameters);

    void setEndpoint(URI endpoint);
    URI getEndpoint();

    void setHttpMethod(HttpMethodName httpMethod);
    HttpMethodName getHttpMethod();

    InputStream getContent();
    void setContent(InputStream content);

}
