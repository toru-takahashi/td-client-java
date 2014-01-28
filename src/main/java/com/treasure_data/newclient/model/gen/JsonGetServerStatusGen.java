package com.treasure_data.newclient.model.gen;

import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.ServerStatus;

public class JsonGetServerStatusGen extends AbstractJsonResponseModelGen<ServerStatus> {

    @Override
    public ServerStatus create(ResponseParser<ServerStatus> p)
            throws TreasureDataClientException {
        Object o = parseJsonObject(p);

        @SuppressWarnings("rawtypes")
        Map map = (Map) o;
        String status = (String) map.get(ServerStatus.STATUS);

        return ServerStatus.createInstance(status);
    }

}
