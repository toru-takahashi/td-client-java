package com.treasure_data.newclient.model.transform;

import java.io.IOException;
import java.io.InputStream;

import com.treasure_data.newclient.TreasureDataClientException;

public class StreamUnmarshaller<M> extends AbstractUnmarshaller<M, InputStream> {

    public StreamUnmarshaller(ResponseParser<M> parser,
            ResponseModelGen<M> init) {
        super(parser, init);
    }

    @Override
    public M unmarshall(InputStream in) throws IOException, TreasureDataClientException {
        parser.parse(init, in);
        return init.create(parser);
    }

}
