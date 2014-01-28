package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.gen.Unmarshaller;

public class DefaultResponseHandler<M> extends AbstractResponseHandler<M> {
    private static final Logger LOG = Logger.getLogger(DefaultResponseHandler.class.getName());

    static {
    }

    public DefaultResponseHandler(Unmarshaller<M, InputStream> responseUnmarshaller) {
        super(responseUnmarshaller);
    }

    public M handle(Response response) throws IOException,
            TreasureDataClientException {
        LOG.log(Level.FINEST, "Parse service response");
        M result = unmarshaller.unmarshall(response.getContent());
        LOG.log(Level.FINEST, "Done parsing service response");
        return result;
    }

}
