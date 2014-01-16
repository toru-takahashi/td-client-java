package com.treasure_data.newclient.model.transform;

import java.util.Map;

import org.json.simple.JSONValue;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.ServerStatus;

public class JsonServerStatusIntializer extends AbstractResponseModelInitializer<ServerStatus> {

    @Override
    public ServerStatus create(ResponseParser<ServerStatus> p)
            throws TreasureDataClientException {
        if (!(p instanceof JsonResponseParser)) {
            throw new TreasureDataClientException(
                    "Internal Error: unexpected response parser is used: " + p);
        }

        JsonResponseParser<ServerStatus> jp = (JsonResponseParser<ServerStatus>) p;

        String text = jp.getJsonText();
        if (text == null) {
            throw new TreasureDataClientException(
                    "Cannot create model object from JSON text: " + text);
        }

        Object o = JSONValue.parse(text);
        Map map = (Map) o;
        String status = (String) map.get("status");

        model = new ServerStatus();
        model.setStatus(status);

        return model;
    }

}
