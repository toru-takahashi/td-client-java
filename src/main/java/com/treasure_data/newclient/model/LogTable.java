package com.treasure_data.newclient.model;

public class LogTable extends Table {

    public LogTable() {
        this.type = Table.Type.LOG;
    }

    public static LogTable createInstance(String databaseName, String tableName) {
        LogTable table = new LogTable();
        table.setDatabaseName(databaseName);
        table.setTableName(tableName);
        return table;
    }
}
