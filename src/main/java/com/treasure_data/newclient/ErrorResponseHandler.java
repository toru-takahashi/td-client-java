package com.treasure_data.newclient;

import java.io.IOException;

import com.treasure_data.newclient.http.HttpResponse;
import com.treasure_data.newclient.http.HttpResponseHandler;

public class ErrorResponseHandler
        implements HttpResponseHandler<TreasureDataServiceException> {

    @Override
    public TreasureDataServiceException handle(HttpResponse errorResponse)
            throws IOException {
        if (errorResponse.getContent() == null) {
            TreasureDataServiceException e = new TreasureDataServiceException(errorResponse.getStatusText());
//            stId = errorResponse.getHeaders().get(Headers.REQUEST_ID);
//            String extendedRequestId = errorResponse.getHeaders().get(Headers.EXTENDED_REQUEST_ID);
//            TreaureDataServiceException e = new TreasureDataServiceException(errorResponse.getStatusText());
//            e.setStatusCode(errorResponse.getStatusCode());
//            e.setRequestId(requestId);
//            e.setExtendedRequestId(extendedRequestId);
            fillInErrorType(e, errorResponse);
            return e;
        }
        // TODO Auto-generated method stub
        return null;
    }

    private void fillInErrorType(TreasureDataServiceException e, HttpResponse errorResponse) {
        if (errorResponse.getStatusCode() >= 500) {
            e.setErrorType(TreasureDataServiceException.ErrorType.Service);
        } else {
            e.setErrorType(TreasureDataServiceException.ErrorType.Client);
        }
    }
}
