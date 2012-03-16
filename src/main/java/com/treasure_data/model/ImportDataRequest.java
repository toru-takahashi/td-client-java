//
// Java Client Library for Treasure Data Cloud
//
// Copyright (C) 2011 - 2012 Muga Nishizawa
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package com.treasure_data.model;

public class ImportDataRequest extends TableSpecifyRequest<ImportDataRequest> {

    public static enum Format {
        MSGPACKGZ, UNKNOWN,
    }

    public static String toFormatName(Format format) {
        switch (format) {
        case MSGPACKGZ:
            return "msgpack.gz";
        default:
            return "unknown";
        }
    }

    public static Format toFormat(String formatName) {
        if (formatName.equals("msgpack.gz")) {
            return Format.MSGPACKGZ;
        } else {
            return Format.UNKNOWN;
        }
    }

    private ByteBuffer data;

    private Format format;

    public ImportDataRequest() {
        super();
    }

    public ImportDataRequest(String databaseName, String tableName, ByteBuffer data, Format format) {
        super(databaseName, tableName);
        super(table);
        this.data = data;
        this.format = format;
    }

    public ByteBuffer getData() {
        return data;
    }

    public Format getFormat() {
        return format;
    }

    public ByteBuffer setData() {
        return data;
    }

    public Format setFormat() {
        return format;
    }

    public ImportDataRequest withData(ByteBuffer data) {
        ImportDataRequest c = (ImportDataRequest) clone();
        c.setData(data);
        return c;
    }

    public ImportDataRequest withFormat(Format format) {
        ImportDataRequest c = (ImportDataRequest) clone();
        c.setFormat(format);
        return c;
    }
}

