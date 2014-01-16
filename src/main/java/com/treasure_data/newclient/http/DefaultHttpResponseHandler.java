package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.TreasureDataServiceResponse;
import com.treasure_data.newclient.model.transform.Unmarshaller;

public class DefaultHttpResponseHandler<M>
        extends AbstractHttpResponseHandler<M> {
    private static final Logger LOG = Logger.getLogger(DefaultHttpResponseHandler.class.getName());

    static {
    }

    public DefaultHttpResponseHandler(Unmarshaller<M, InputStream> responseUnmarshaller) {
        super(responseUnmarshaller);
    }

    public TreasureDataServiceResponse<M> handle(HttpResponse httpResponse)
            throws IOException, TreasureDataClientException {
        TreasureDataServiceResponse<M> response = new TreasureDataServiceResponse<M>();
        responseHeaders = httpResponse.getHeaders();

        if (responseUnmarshaller != null) {
            LOG.log(Level.FINEST, "Parse service response");
            M result = responseUnmarshaller.unmarshall(httpResponse.getContent());
            LOG.log(Level.FINEST, "Done parsing service response");
            response.setResult(result);
        }

        return response;
    }

    public Map<String, String> getResourseHeaders() {
        return responseHeaders;
    }

}
