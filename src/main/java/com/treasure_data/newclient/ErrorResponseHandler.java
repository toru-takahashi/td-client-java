package com.treasure_data.newclient;

import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.treasure_data.newclient.http.AbstractHttpResponseHandler;
import com.treasure_data.newclient.http.HttpResponse;
import com.treasure_data.newclient.model.transform.Unmarshaller;

public class ErrorResponseHandler
        extends AbstractHttpResponseHandler<TreasureDataServiceException> {
    private static final Logger LOG = Logger.getLogger(ErrorResponseHandler.class.getName());

    public ErrorResponseHandler(Unmarshaller<TreasureDataServiceException, InputStream> responseUnmarshaller) {
        super(responseUnmarshaller);
    }

    @Override
    public TreasureDataServiceResponse<TreasureDataServiceException> handle(
            HttpResponse errorResponse)
            throws IOException, TreasureDataClientException {
        TreasureDataServiceResponse<TreasureDataServiceException> response =
                new TreasureDataServiceResponse<TreasureDataServiceException>();
        responseHeaders = errorResponse.getHeaders();

        if (errorResponse.getContent() != null) {
            if (responseUnmarshaller != null) {
                LOG.log(Level.FINEST, "Parse service response");
                TreasureDataServiceException e =
                        responseUnmarshaller.unmarshall(errorResponse.getContent());
                LOG.log(Level.FINEST, "Done parsing service response");
                response.setResult(e);
            }
        }

        return response;
    }
}
