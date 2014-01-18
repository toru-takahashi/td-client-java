package com.treasure_data.newclient.model.transform;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractResponseParser<M> implements ResponseParser<M> {

    public AbstractResponseParser() {
    }

    public abstract void parse(ResponseModelInitializer<M> init, InputStream in)
            throws IOException;
}
