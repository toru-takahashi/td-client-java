package com.treasure_data.newclient.model.transform;

import java.io.IOException;
import java.io.InputStream;

import com.treasure_data.newclient.TreasureDataClientException;

public class DefaultUnmarshaller<M> extends AbstractUnmarshaller<M, InputStream> {

    public DefaultUnmarshaller(ResponseParser<M> parser,
            ResponseModelInitializer<M> init) {
        super(parser, init);
    }

    @Override
    public M unmarshall(InputStream in) throws IOException, TreasureDataClientException {
        parser.parseInputStream(init, in);
        return init.create(parser);
    }

}
