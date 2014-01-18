package com.treasure_data.newclient.model.transform;

import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.ServerStatus;

public class JsonGetServerStatusIntializer extends AbstractJsonResponseModelInitializer<ServerStatus> {

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
