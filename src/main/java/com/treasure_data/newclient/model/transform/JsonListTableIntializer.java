package com.treasure_data.newclient.model.transform;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.Table;

public class JsonListTableIntializer extends AbstractResponseModelInitializer<List<Table>> {

    public JsonListTableIntializer() {
    }

    public List<Table> create(ResponseParser<List<Table>> p)
                    throws TreasureDataClientException {
        if (!(p instanceof JsonResponseParser)) {
            throw new TreasureDataClientException(
                    "Internal Error: unexpected response parser is used: " + p);
        }

        JsonResponseParser<List<Table>> jp = (JsonResponseParser<List<Table>>) p;

        String text = jp.getJsonText();
        if (text == null) {
            throw new TreasureDataClientException(
                    "Internal Error: JSON text is null.");
        }

        // {
        //   "tables": [
        //      { "id":91812,
        //        "name":"dac_filenotfound",
        //        "estimated_storage_size":0,
        //        "counter_updated_at":null,
        //        "last_log_timestamp":null,
        //        "expire_days":null,
        //        "type":"log",
        //        "count":0,
        //        "created_at":"2013-12-15 14:33:48 -0800",
        //        "updated_at":"2013-12-15 14:33:48 -0800",
        //        "schema":"[]"},
        //       {"id":79283,"name":"dollartbl","estimated_storage_size":0,"counter_updated_at":"2013-09-02T21:05:11-07:00","last_log_timestamp":"2013-06-28T02:54:06-07:00","expire_days":null,"type":"log","count":8,"created_at":"2013-09-02 21:03:10 -0700","updated_at":"2013-09-02 21:05:11 -0700","schema":"[]"},
        //       {"id":85262,"name":"foo","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":0,"created_at":"2013-10-14 18:25:22 -0700","updated_at":"2013-10-14 18:33:53 -0700","schema":"[[\"b\",\"string\"],[\"c\",\"int\"]]"},
        //       {"id":80769,"name":"json_table","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":2,"created_at":"2013-09-13 02:16:50 -0700","updated_at":"2013-09-13 02:19:22 -0700","schema":"[]"},{"id":82686,"name":"json_table_20130925_aiming","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1,"created_at":"2013-09-24 20:00:30 -0700","updated_at":"2013-09-24 20:03:36 -0700","schema":"[]"},{"id":82693,"name":"json_table_20130925_aiming2","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1,"created_at":"2013-09-24 20:09:52 -0700","updated_at":"2013-09-24 20:11:48 -0700","schema":"[]"},{"id":82694,"name":"json_table_20130925_aiming3","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1,"created_at":"2013-09-24 20:14:58 -0700","updated_at":"2013-09-24 20:16:14 -0700","schema":"[]"},{"id":82704,"name":"json_table_20130925_aiming4","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1,"created_at":"2013-09-24 22:11:43 -0700","updated_at":"2013-09-24 22:12:33 -0700","schema":"[]"},{"id":82705,"name":"json_table_20130925_aiming5","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1,"created_at":"2013-09-24 22:14:22 -0700","updated_at":"2013-09-24 22:15:17 -0700","schema":"[]"},{"id":79475,"name":"keywordcoltbl","estimated_storage_size":0,"counter_updated_at":"2013-09-03T19:50:54-07:00","last_log_timestamp":"2013-06-28T02:54:06-07:00","expire_days":null,"type":"log","count":10,"created_at":"2013-09-03 19:46:57 -0700","updated_at":"2013-09-03 19:53:47 -0700","schema":"[[\"percent\",\"string\"]]"},{"id":75348,"name":"persenttbl","estimated_storage_size":0,"counter_updated_at":"2013-08-08T23:56:58-07:00","last_log_timestamp":"2013-06-28T02:54:06-07:00","expire_days":null,"type":"log","count":10,"created_at":"2013-08-08 23:46:49 -0700","updated_at":"2013-08-08 23:56:58 -0700","schema":"[]"},{"id":53090,"name":"posdata","estimated_storage_size":2184582,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":100000,"created_at":"2013-05-04 02:42:43 -0700","updated_at":"2013-09-30 19:44:37 -0700","schema":"[]"},{"id":69738,"name":"result","estimated_storage_size":4710875,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":100000,"created_at":"2013-07-05 02:44:29 -0700","updated_at":"2013-09-30 19:45:44 -0700","schema":"[]"},{"id":21983,"name":"score","estimated_storage_size":0,"counter_updated_at":"2013-01-26T02:30:29-08:00","last_log_timestamp":null,"expire_days":null,"type":"log","count":100,"created_at":"2013-01-26 02:30:28 -0800","updated_at":"2013-10-31 22:49:56 -0700","schema":"[[\"id\",\"string\"],[\"name\",\"string\"],[\"score\",\"string\"],[\"foo\",\"string\"],[\"bar\",\"array<string>\"],[\"jar\",\"array<string>\"]]"},{"id":75858,"name":"sesstest","estimated_storage_size":506448587,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":30001350,"created_at":"2013-08-15 02:08:15 -0700","updated_at":"2013-12-13 16:20:30 -0800","schema":"[]"},{"id":88667,"name":"sesstest2","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":1260,"created_at":"2013-11-13 18:52:34 -0800","updated_at":"2013-12-19 15:09:20 -0800","schema":"[]"},{"id":87363,"name":"test01","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"item","count":0,"created_at":"2013-10-30 20:44:48 -0700","updated_at":"2013-10-30 20:44:48 -0700","schema":"[]","primary_key":"key","primary_key_type":"string"},{"id":65434,"name":"testtbl","estimated_storage_size":134179,"counter_updated_at":"2013-08-15T02:40:50-07:00","last_log_timestamp":"2013-06-28T02:54:06-07:00","expire_days":null,"type":"log","count":10500,"created_at":"2013-06-24 00:05:04 -0700","updated_at":"2013-09-30 19:45:05 -0700","schema":"[]"},{"id":33953,"name":"users","estimated_storage_size":0,"counter_updated_at":"2013-03-08T01:43:59-08:00","last_log_timestamp":null,"expire_days":null,"type":"log","count":1000,"created_at":"2013-03-08 01:43:58 -0800","updated_at":"2013-03-08 01:43:59 -0800","schema":"[]"},{"id":32634,"name":"wikistats3","estimated_storage_size":0,"counter_updated_at":null,"last_log_timestamp":null,"expire_days":null,"type":"log","count":0,"created_at":"2013-03-03 23:50:14 -0800","updated_at":"2013-03-03 23:50:14 -0800","schema":"[]"},{"id":71966,"name":"www_access","estimated_storage_size":0,"counter_updated_at":"2013-07-23T02:45:24-07:00","last_log_timestamp":"2013-06-28T02:54:06-07:00","expire_days":null,"type":"log","count":5000,"created_at":"2013-07-23 02:45:19 -0700","updated_at":"2013-12-20 12:38:37 -0800","schema":"[[\"host\",\"string\"]]"}
        //   ],
        //   "database":"mugadb"
        // }

        Object o = JSONValue.parse(text);
        if (o == null) {
            throw new TreasureDataClientException(
                    "Cannot create model object from JSON text: " + text);
        }

        // TODO FIXME
        return model;
    }
}
