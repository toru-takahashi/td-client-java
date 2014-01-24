package com.treasure_data.newclient.model.transform;

import java.io.IOException;

import com.treasure_data.newclient.TreasureDataClientException;

public abstract class AbstractUnmarshaller<M, I> implements Unmarshaller<M, I> {
    protected ResponseParser<M> parser;
    protected ResponseModelGen<M> init;

    public AbstractUnmarshaller(ResponseParser<M> parser, ResponseModelGen<M> init) {
        this.parser = parser;
        this.init = init;
    }

    public abstract M unmarshall(I in) throws IOException, TreasureDataClientException;

}
