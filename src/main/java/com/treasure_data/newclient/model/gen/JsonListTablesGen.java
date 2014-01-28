package com.treasure_data.newclient.model.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.ItemTable;
import com.treasure_data.newclient.model.LogTable;
import com.treasure_data.newclient.model.Table;

public class JsonListTablesGen extends AbstractJsonResponseModelGen<List<Table>> {

    public JsonListTablesGen() {
    }

    public List<Table> create(ResponseParser<List<Table>> p)
                    throws TreasureDataClientException {
        Object o = parseJsonObject(p);

        model = new ArrayList<Table>();

        @SuppressWarnings("rawtypes")
        Map m = (Map) o;
        String databaseName = (String) m.get(Table.DATABASE);
        @SuppressWarnings("rawtypes")
        List tables = (List) m.get(Table.TABLES);
        @SuppressWarnings("rawtypes")
        Iterator iter = (Iterator) tables.iterator();
        while (iter.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map table = (Map) iter.next();
            String tableName = (String) table.get(Table.NAME);
            Table.Type type = Table.Type.fromString((String) table.get(Table.TYPE));

            if (type.equals(Table.Type.LOG)) { // log
                model.add(ItemTable.createInstance(databaseName, tableName));
            } else { // item
                model.add(LogTable.createInstance(databaseName, tableName));
            }
        }

        return model;
    }
}
