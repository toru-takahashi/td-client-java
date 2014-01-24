package com.treasure_data.newclient.http;

import java.io.IOException;

import com.treasure_data.newclient.TreasureDataClientException;

public interface ResponseHandler<M> {

    M handle(Response response) throws IOException, TreasureDataClientException;
}
