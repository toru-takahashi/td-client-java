package com.treasure_data.newclient;

import java.io.InputStream;
import java.util.Map;

import com.treasure_data.newclient.auth.Signer;
import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public interface Request<T extends TreasureDataServiceRequest> {

    public static enum MethodName {
        GET, PUT, POST;
    }

    TreasureDataServiceRequest getOriginalRequest();

    void setCredentials(TreasureDataCredentials credentials);
    TreasureDataCredentials getCredentials();
    void setSigner(Signer signer);
    Signer getSinger();

    void addHeader(String name, String value);
    Map<String, String> getHeaders();
    void setHeaders(Map<String, String> headers);

    void setResourcePath(String path);
    String getResourcePath();

    void addParameter(String name, String value);
    Map<String, String> getParameters();
    void setParameters(Map<String, String> parameters);

    void setEndpoint(String endpoint);
    String getEndpoint();

    void setMethodName(MethodName methodName);
    MethodName getMethodName();

    InputStream getContent();
    void setContent(InputStream content);

}
