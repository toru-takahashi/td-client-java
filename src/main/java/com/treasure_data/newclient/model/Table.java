package com.treasure_data.newclient.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Table {
    public static final String TABLES = "tables";
    public static final String TABLE = "table";
    public static final String DATABASE = "database";
    public static final String NAME = "NAME";
    public static final String TYPE = "type";

    public static enum Type {
        LOG("log"), ITEM("item");

        private String type;

        Type(String type) {
            this.type = type;
        }

        public String type() {
            return type;
        }

        public static Type fromString(String type) {
            return StringToType.get(type);
        }

        private static class StringToType {
            private static final Map<String, Type> REVERSE_DICTIONARY;

            static {
                Map<String, Type> map = new HashMap<String, Type>();
                for (Type e : Type.values()) {
                    map.put(e.type(), e);
                }
                REVERSE_DICTIONARY = Collections.unmodifiableMap(map);
            }

            static Type get(String type) {
                return REVERSE_DICTIONARY.get(type);
            }
        }
    }

    protected String databaseName;
    protected String tableName;
    protected Type type;

    Table() {
    }

    void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public Type getType() {
        return type;
    }
}

