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
    public TreasureDataServiceResponse<TreasureDataServiceException> handle(HttpResponse errorResponse)
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

//            Document document = XpathUtils.documentFrom(errorResponse.getContent());
//            String message = XpathUtils.asString("Error/Message", document);
//            String errorCode = XpathUtils.asString("Error/Code", document);
//            String requestId = XpathUtils.asString("Error/RequestId", document);
//            String extendedRequestId = XpathUtils.asString("Error/HostId", document);
//
//            AmazonS3Exception ase = new AmazonS3Exception(message);
//            ase.setStatusCode(errorResponse.getStatusCode());
//            ase.setErrorCode(errorCode);
//            ase.setRequestId(requestId);
//            ase.setExtendedRequestId(extendedRequestId);
//            fillInErrorType(ase, errorResponse);
//
//            return ase;
        } else {
            TreasureDataServiceException e = new TreasureDataServiceException(errorResponse.getStatusText());
//            stId = errorResponse.getHeaders().get(Headers.REQUEST_ID);
//            String extendedRequestId = errorResponse.getHeaders().get(Headers.EXTENDED_REQUEST_ID);
//            TreaureDataServiceException e = new TreasureDataServiceException(errorResponse.getStatusText());
//            e.setStatusCode(errorResponse.getStatusCode());
//            e.setRequestId(requestId);
//            e.setExtendedRequestId(extendedRequestId);
            fillInErrorType(e, errorResponse);
            response.setResult(e);
        }

        return response;
    }

    private void fillInErrorType(TreasureDataServiceException e, HttpResponse errorResponse) {
        if (errorResponse.getStatusCode() >= 500) {
            e.setErrorType(TreasureDataServiceException.ErrorType.Service);
        } else {
            e.setErrorType(TreasureDataServiceException.ErrorType.Client);
        }
    }
}
