package com.treasure_data.newclient.model;

public class ItemTable extends Table {

    public ItemTable() {
        type = Table.Type.ITEM;
    }

    public static ItemTable createInstance(String databaseName, String tableName) {
        ItemTable table = new ItemTable();
        table.setDatabaseName(databaseName);
        table.setTableName(tableName);
        // TODO FIXME primary key
        // TODO FIXME primary key type
        return table;
    }
}
