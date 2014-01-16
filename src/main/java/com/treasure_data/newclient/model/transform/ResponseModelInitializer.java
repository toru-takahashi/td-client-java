package com.treasure_data.newclient.model.transform;

import com.treasure_data.newclient.TreasureDataClientException;

public interface ResponseModelInitializer<M> {

    M create(ResponseParser<M> p) throws TreasureDataClientException;
}
