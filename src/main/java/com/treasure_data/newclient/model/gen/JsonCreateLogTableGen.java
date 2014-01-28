package com.treasure_data.newclient.model.gen;

import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.LogTable;
import com.treasure_data.newclient.model.Table;

public class JsonCreateLogTableGen extends
        AbstractJsonResponseModelGen<LogTable> {

    @Override
    public LogTable create(ResponseParser<LogTable> p)
            throws TreasureDataClientException {
        Object o = parseJsonObject(p);

        Map m = (Map) o;
        String databaseName = (String) m.get(Table.DATABASE);
        String tableName = (String) m.get(Table.TABLE);
        Table.Type type = Table.Type.fromString((String) m.get(Table.TYPE));

        if (type.equals(Table.Type.LOG)) { // log
            return LogTable.createInstance(databaseName, tableName);
        } else { // item
            return null; // TODO
        }
    }

}
