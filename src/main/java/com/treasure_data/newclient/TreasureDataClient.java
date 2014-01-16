package com.treasure_data.newclient;

import java.util.List;
import java.util.Map.Entry;

import com.treasure_data.newclient.auth.DefaultSigner;
import com.treasure_data.newclient.auth.Signer;
import com.treasure_data.newclient.auth.TreasureDataCredentials;
import com.treasure_data.newclient.http.ExecutionContext;
import com.treasure_data.newclient.http.HttpMethodName;
import com.treasure_data.newclient.http.HttpResponseHandler;
import com.treasure_data.newclient.http.DefaultHttpResponseHandler;
import com.treasure_data.newclient.http.ResourcePath;
import com.treasure_data.newclient.model.ListTablesRequest;
import com.treasure_data.newclient.model.ServerStatus;
import com.treasure_data.newclient.model.Table;
import com.treasure_data.newclient.model.transform.JsonListTableIntializer;
import com.treasure_data.newclient.model.transform.JsonResponseParser;
import com.treasure_data.newclient.model.GetServerStatusRequest;
import com.treasure_data.newclient.model.transform.DefaultUnmarshaller;
import com.treasure_data.newclient.model.transform.JsonServerStatusIntializer;
import com.treasure_data.newclient.model.transform.ResponseModelInitializer;
import com.treasure_data.newclient.model.transform.ResponseParser;

public class TreasureDataClient extends AbstractTreasureDataClient {

    private ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();

    public TreasureDataClient(Configuration conf) throws TreasureDataClientException {
        super(conf);
    }

    public ServerStatus getServerStatus()
        throws TreasureDataClientException, TreasureDataServiceException {
        GetServerStatusRequest request = new GetServerStatusRequest();
        final String resourcePath = String.format(ResourcePath.V3_SERVER_STATUS);

        Request<GetServerStatusRequest> req = createRequest(resourcePath, request,
                HttpMethodName.GET);

        return invoke(req, new JsonResponseParser<ServerStatus>(),
                new JsonServerStatusIntializer());
    }

    public List<Table> listTables(String databaseName)
            throws TreasureDataClientException, TreasureDataServiceException {
        ListTablesRequest request = new ListTablesRequest();
        request.setDatabaseName(databaseName);
        return listTables(request);
    }

    public List<Table> listTables(ListTablesRequest request)
            throws TreasureDataClientException, TreasureDataServiceException {
        if (request == null) {
            throw new NullPointerException("ListTablesRequest object is null");
        }

        // validate database name
        request.validate();

        // build resource path
        final String resourcePath = String.format(ResourcePath.V3_TABLES_LIST,
                request.getDatabaseName());

        // create request
        Request<ListTablesRequest> req = createRequest(resourcePath, request,
                HttpMethodName.GET);

        return invoke(req, new JsonResponseParser<List<Table>>(),
                new JsonListTableIntializer());
    }

    protected <REQ extends TreasureDataServiceRequest> Request<REQ> createRequest(
            final String resourcePath, final REQ request, final HttpMethodName m) {
        Request<REQ> req = new DefaultRequest<REQ>(request);
        req.setHttpMethod(m);
        req.setResourcePath(resourcePath);
        req.setEndpoint(endpoint);
        return req;
    }

    protected <M, REQ extends TreasureDataServiceRequest> M invoke(
            Request<REQ> request,
            ResponseParser<M> responseParser,
            ResponseModelInitializer<M> init)
                    throws TreasureDataServiceException, TreasureDataClientException {
        return invoke(request, new DefaultHttpResponseHandler<M>(
                new DefaultUnmarshaller<M>(responseParser, init)));
    }

    protected <M, REQ extends TreasureDataServiceRequest> M invoke(
            Request<REQ> request,
            HttpResponseHandler<TreasureDataServiceResponse<M>> responseHandler)
                    throws TreasureDataServiceException, TreasureDataClientException {
        for (Entry<String, String> entry :
                request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

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
        TreasureDataServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && originalRequest.getCredentials() != null) {
            credentials = originalRequest.getCredentials();
        }

        ExecutionContext executionContext = createExecutionContext();
        executionContext.setSigner(createSigner(request));
        executionContext.setCredentials(credentials);

        return (M) client.execute(request, responseHandler,
                errorResponseHandler, executionContext);
    }

    protected <REQ> Signer createSigner(Request<REQ> request) {
        return new DefaultSigner();
    }
}
