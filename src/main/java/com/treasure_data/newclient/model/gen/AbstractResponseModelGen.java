package com.treasure_data.newclient.model.gen;

import com.treasure_data.newclient.TreasureDataClientException;

public abstract class AbstractResponseModelGen<M> implements ResponseModelGen<M> {

    protected M model;

    public AbstractResponseModelGen() {
    }

    public abstract M create(ResponseParser<M> p) throws TreasureDataClientException;
}
