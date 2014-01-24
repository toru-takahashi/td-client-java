package com.treasure_data.newclient.model.transform;

import com.treasure_data.newclient.TreasureDataClientException;

public abstract class AbstractResponseModelInitializer<M> implements ResponseModelGen<M> {

    protected M model;

    public AbstractResponseModelInitializer() {
    }

    public abstract M create(ResponseParser<M> p) throws TreasureDataClientException;
}
