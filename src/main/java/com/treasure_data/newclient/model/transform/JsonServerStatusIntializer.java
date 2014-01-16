package com.treasure_data.newclient.model.transform;

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
        System.out.println("text: " + text);

        return model;
    }

}
