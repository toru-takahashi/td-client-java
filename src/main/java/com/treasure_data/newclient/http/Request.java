package com.treasure_data.newclient.http;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import com.treasure_data.newclient.TreasureDataServiceRequest;

public interface Request<T> {

    public static enum MethodName {
        GET, PUT, POST;
    }

    TreasureDataServiceRequest getOriginalRequest();

    void addHeader(String name, String value);
    Map<String, String> getHeaders();
    void setHeaders(Map<String, String> headers);

    void setResourcePath(String path);
    String getResourcePath();

    void addParameter(String name, String value);
    Map<String, String> getParameters();
    void setParameters(Map<String, String> parameters);

    void setEndpoint(URI endpoint);
    URI getEndpoint();

    void setMethodName(MethodName methodName);
    MethodName getMethodName();

    InputStream getContent();
    void setContent(InputStream content);

}
