package com.treasure_data.newclient;

import java.util.List;

import com.treasure_data.newclient.model.CreateLogTableRequest;
import com.treasure_data.newclient.model.ListTablesRequest;
import com.treasure_data.newclient.model.LogTable;
import com.treasure_data.newclient.model.ServerStatus;
import com.treasure_data.newclient.model.Table;
import com.treasure_data.newclient.model.gen.JsonCreateLogTableGen;
import com.treasure_data.newclient.model.gen.JsonGetServerStatusGen;
import com.treasure_data.newclient.model.gen.JsonListTablesGen;
import com.treasure_data.newclient.model.GetServerStatusRequest;

public class TreasureDataClient extends AbstractTreasureDataClient {

    public TreasureDataClient(Configuration conf) throws TreasureDataClientException {
        super(conf);
    }

    public ServerStatus getServerStatus()
        throws TreasureDataClientException, TreasureDataServiceException {
        GetServerStatusRequest request = new GetServerStatusRequest();
        return invoke(request, new JsonGetServerStatusGen(), Request.MethodName.GET);
    }

    public List<Table> listTables(String databaseName)
            throws TreasureDataClientException, TreasureDataServiceException {
        ListTablesRequest request = new ListTablesRequest();
        request.setDatabaseName(databaseName);
        return listTables(request);
    }

    public List<Table> listTables(ListTablesRequest request)
            throws TreasureDataClientException, TreasureDataServiceException {
        return invoke(request, new JsonListTablesGen(), Request.MethodName.GET);
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
        return invoke(request, new JsonCreateLogTableGen(), Request.MethodName.POST);
    }

//    public ItemTable createItemTable(CreateItemTableRequest request)
//            throws TreasureDataClientException, TreasureDataServiceException {
//        return invoke(request, new JsonCreateItemTableInitializer(), HttpMethodName.POST);
//    }


}
