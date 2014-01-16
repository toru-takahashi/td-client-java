package com.treasure_data.newclient.model.transform;

import java.io.IOException;

import com.treasure_data.newclient.TreasureDataClientException;

public abstract class AbstractUnmarshaller<M, I> implements Unmarshaller<M, I> {
    protected ResponseParser<M> parser;
    protected ResponseModelInitializer<M> init;

    public AbstractUnmarshaller(ResponseParser<M> parser, ResponseModelInitializer<M> init) {
        this.parser = parser;
        this.init = init;
    }

    public abstract M unmarshall(I in) throws IOException, TreasureDataClientException;

}
