package com.treasure_data.newclient.http;

import java.io.IOException;

import com.treasure_data.newclient.TreasureDataClientException;

public interface HttpResponseHandler<T> {

    T handle(HttpResponse response) throws IOException, TreasureDataClientException;
}
