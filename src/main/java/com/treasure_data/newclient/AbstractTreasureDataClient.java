package com.treasure_data.newclient;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

import com.treasure_data.client.Constants;
import com.treasure_data.newclient.auth.DefaultSigner;
import com.treasure_data.newclient.auth.DefaultTreasureDataCredentials;
import com.treasure_data.newclient.auth.Signer;
import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.http.DefaultResponseHandler;
import com.treasure_data.newclient.http.ResponseHandler;
import com.treasure_data.newclient.http.TreasureDataHttpClient;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;
import com.treasure_data.newclient.model.gen.JsonExceptionGen;
import com.treasure_data.newclient.model.gen.JsonResponseParser;
import com.treasure_data.newclient.model.gen.ResponseModelGen;
import com.treasure_data.newclient.model.gen.ResponseParser;
import com.treasure_data.newclient.model.gen.StreamUnmarshaller;

public abstract class AbstractTreasureDataClient implements Closeable {

    private static final Logger LOG = Logger.getLogger(AbstractTreasureDataClient.class.getName());

    protected TreasureDataCredentials credentials;
    protected Configuration conf;
    protected TreasureDataHttpClient client;

    public AbstractTreasureDataClient(Configuration conf)
            throws TreasureDataClientException {
        this.conf = conf;
        this.client = new TreasureDataHttpClient(conf);
    }

    protected TreasureDataCredentials getCredentials() {
        if (credentials != null) {
            return credentials;
        }

        final String apiKey = conf.getProperties().getProperty(Constants.TD_API_KEY);
        // TODO FIXME null check of api key
        credentials = new DefaultTreasureDataCredentials(apiKey);
        return credentials;
    }

    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    protected <M, REQ extends TreasureDataServiceRequest> M invoke(
            REQ originalRequest,
            ResponseModelGen<M> modelGen,
            Request.MethodName m)
                    throws TreasureDataClientException, TreasureDataServiceException {
        if (originalRequest == null) {
            throw new NullPointerException("origina request is null.");
        }

        originalRequest.validate();

        Request<REQ> request = createRequest(originalRequest, m);
        ResponseHandler<M> responseHandler =
                createResponseHandler(createUnmarshaller(new JsonResponseParser<M>(), modelGen));
        ResponseHandler<TreasureDataServiceException> errorResponseHandler =
                createErrorResponseHandler();
        return invoke(request, responseHandler, errorResponseHandler);
    }

    protected <M, REQ extends TreasureDataServiceRequest> M invoke(
            Request<REQ> request,
            ResponseHandler<M> responseHandler,
            ResponseHandler<TreasureDataServiceException> errorResponseHandler)
                    throws TreasureDataServiceException, TreasureDataClientException {
        /*
         * The string we sign needs to include the exact headers that we
         * send with the request, but the client runtime layer adds the
         * Content-Type header before the request is sent if one isn't set, so
         * we have to set something here otherwise the request will fail.
         */
        if (request.getHeaders().get("Content-Type") == null) {
            request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        }

        TreasureDataCredentials credentials = getCredentials();
        request.setSigner(createSigner(request));
        request.setCredentials(credentials);

        return (M) client.execute(request, responseHandler,
                errorResponseHandler);
    }

    protected <REQ extends TreasureDataServiceRequest> Request<REQ> createRequest(
            REQ originalRequest, Request.MethodName m) {
        Request<REQ> request = new DefaultRequest<REQ>(originalRequest);
        request.setResourcePath(originalRequest.getResourcePath());
        request.setMethodName(m);
        request.setEndpoint(conf.getEndpoint());
        return request;
    }

    protected <M> StreamUnmarshaller<M> createUnmarshaller(ResponseParser<M> parser, ResponseModelGen<M> modelGen) {
        return new StreamUnmarshaller<M>(parser, modelGen);
    }

    protected <M> ResponseHandler<M> createResponseHandler(StreamUnmarshaller<M> unmarshaller) {
        return new DefaultResponseHandler<M>(unmarshaller);
    }

    protected ResponseHandler<TreasureDataServiceException> createErrorResponseHandler() {
        return new DefaultResponseHandler<TreasureDataServiceException>(
                new StreamUnmarshaller<TreasureDataServiceException>(
                        new JsonResponseParser<TreasureDataServiceException>(),
                        new JsonExceptionGen()));
    }

    protected <REQ extends TreasureDataServiceRequest> Signer createSigner(Request<REQ> request) {
        return new DefaultSigner();
    }
}
