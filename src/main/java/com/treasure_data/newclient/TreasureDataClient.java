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
import com.treasure_data.newclient.model.CreateLogTableRequest;
import com.treasure_data.newclient.model.ListTablesRequest;
import com.treasure_data.newclient.model.LogTable;
import com.treasure_data.newclient.model.ServerStatus;
import com.treasure_data.newclient.model.Table;
import com.treasure_data.newclient.model.transform.JsonListTablesIntializer;
import com.treasure_data.newclient.model.transform.JsonResponseParser;
import com.treasure_data.newclient.model.GetServerStatusRequest;
import com.treasure_data.newclient.model.transform.DefaultUnmarshaller;
import com.treasure_data.newclient.model.transform.JsonCreateLogTableInitializer;
import com.treasure_data.newclient.model.transform.JsonGetServerStatusIntializer;
import com.treasure_data.newclient.model.transform.ResponseModelInitializer;

public class TreasureDataClient extends AbstractTreasureDataClient {

    private ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();

    public TreasureDataClient(Configuration conf) throws TreasureDataClientException {
        super(conf);
    }

    public ServerStatus getServerStatus()
        throws TreasureDataClientException, TreasureDataServiceException {
        GetServerStatusRequest request = new GetServerStatusRequest();
        return invoke(request, new JsonGetServerStatusIntializer(), HttpMethodName.GET);
    }

    public List<Table> listTables(String databaseName)
            throws TreasureDataClientException, TreasureDataServiceException {
        ListTablesRequest request = new ListTablesRequest();
        request.setDatabaseName(databaseName);
        return listTables(request);
    }

    public List<Table> listTables(ListTablesRequest request)
            throws TreasureDataClientException, TreasureDataServiceException {
        return invoke(request, new JsonListTablesIntializer(), HttpMethodName.GET);
    }

    public LogTable createLogTable(String databaseName, String tableName)
            throws TreasureDataClientException, TreasureDataServiceException {
        CreateLogTableRequest request = new CreateLogTableRequest();
        request.setDatabaseName(databaseName);
        request.setTableName(tableName);
        return createLogTable(request);
    }

    public LogTable createLogTable(CreateLogTableRequest request)
            throws TreasureDataClientException, TreasureDataServiceException {
        return invoke(request, new JsonCreateLogTableInitializer(), HttpMethodName.POST);
    }

//    public ItemTable createItemTable(CreateItemTableRequest request)
//            throws TreasureDataClientException, TreasureDataServiceException {
//        return invoke(request, new JsonCreateItemTableInitializer(), HttpMethodName.POST);
//    }

    protected <M, REQ extends TreasureDataServiceRequest> M invoke(REQ originalRequest,
            ResponseModelInitializer<M> modelInit, HttpMethodName m)
            throws TreasureDataClientException, TreasureDataServiceException {
        if (originalRequest == null) {
            throw new NullPointerException("origina request is null.");
        }

        originalRequest.validate();

        Request<REQ> request = createRequest(originalRequest, m);
        DefaultUnmarshaller<M> unmarshaller = new DefaultUnmarshaller<M>(
                new JsonResponseParser<M>(), modelInit);
        HttpResponseHandler<TreasureDataServiceResponse<M>> responseHandler =
                new DefaultHttpResponseHandler<M>(unmarshaller);
        return invoke(request, responseHandler);
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

    protected <REQ extends TreasureDataServiceRequest> Request<REQ> createRequest(
            REQ originalRequest, HttpMethodName m) {
        Request<REQ> request = new DefaultRequest<REQ>(originalRequest);
        request.setResourcePath(originalRequest.getResourcePath());
        request.setHttpMethod(m);
        request.setEndpoint(endpoint);
        return request;
    }

    protected <REQ> Signer createSigner(Request<REQ> request) {
        return new DefaultSigner();
    }
}
