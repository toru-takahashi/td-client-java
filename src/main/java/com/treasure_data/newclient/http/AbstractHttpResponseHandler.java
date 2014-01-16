package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.TreasureDataServiceResponse;
import com.treasure_data.newclient.model.transform.Unmarshaller;

public abstract class AbstractHttpResponseHandler<M>
        implements HttpResponseHandler<TreasureDataServiceResponse<M>> {

    protected Unmarshaller<M, InputStream> responseUnmarshaller;
    protected Map<String, String> responseHeaders;

    public AbstractHttpResponseHandler(Unmarshaller<M, InputStream> responseUnmarshaller) {
        this.responseUnmarshaller = responseUnmarshaller;
    }
    public Map<String, String> getResourseHeaders() {
        return responseHeaders;
    }

    public abstract TreasureDataServiceResponse<M> handle(HttpResponse response)
            throws IOException, TreasureDataClientException;

}
