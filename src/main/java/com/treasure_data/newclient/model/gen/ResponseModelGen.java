package com.treasure_data.newclient.model.gen;

import com.treasure_data.newclient.TreasureDataClientException;

public interface ResponseModelGen<M> {

    M create(ResponseParser<M> p) throws TreasureDataClientException;
}
