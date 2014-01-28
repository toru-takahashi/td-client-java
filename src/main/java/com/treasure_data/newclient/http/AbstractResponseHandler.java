package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.InputStream;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.gen.Unmarshaller;

public abstract class AbstractResponseHandler<M>
        implements ResponseHandler<M> {

    protected Unmarshaller<M, InputStream> unmarshaller;

    public AbstractResponseHandler(Unmarshaller<M, InputStream> unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public abstract M handle(Response response)
            throws IOException, TreasureDataClientException;

}
