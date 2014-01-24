package com.treasure_data.newclient.model.transform;

import java.util.Map;

import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.TreasureDataServiceException;

public class JsonExceptionGen extends
        AbstractJsonResponseModelInitializer<TreasureDataServiceException> {

    private static final String MESSAGE = "message";

    @Override
    public TreasureDataServiceException create(
            ResponseParser<TreasureDataServiceException> p)
            throws TreasureDataClientException {
        Object o = parseJsonObject(p);
        @SuppressWarnings("rawtypes")
        Map map = (Map) o;
        String message = (String) map.get(MESSAGE);

        TreasureDataServiceException e = new TreasureDataServiceException(message);
        return e;
    }

}
