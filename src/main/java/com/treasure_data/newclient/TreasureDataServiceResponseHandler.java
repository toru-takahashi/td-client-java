package com.treasure_data.newclient;

import com.treasure_data.newclient.http.HttpResponse;

public interface TreasureDataServiceResponseHandler<T> {
    public T handle(HttpResponse response) throws Exception;

    public boolean needsConnectionLeftOpen();
}
