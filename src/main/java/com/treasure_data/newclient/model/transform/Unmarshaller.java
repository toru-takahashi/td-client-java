package com.treasure_data.newclient.model.transform;

import java.io.IOException;

import com.treasure_data.newclient.TreasureDataClientException;

public interface Unmarshaller<M, I> {

    M unmarshall(I in) throws IOException, TreasureDataClientException;
}
