package com.treasure_data.newclient.model.transform;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONValue;

import com.treasure_data.newclient.TreasureDataClientException;

public abstract class AbstractJsonResponseModelInitializer<M> extends AbstractResponseModelInitializer<M> {
    private static final Logger LOG = Logger.getLogger(AbstractJsonResponseModelInitializer.class.getName());

    protected Object parseJsonObject(ResponseParser<M> p) throws TreasureDataClientException {
        validateResponseParser(p);
        JsonResponseParser<M> jp = (JsonResponseParser<M>) p;

        String text = jp.getJsonText();
        validateJsonText(text);

        Object o = getJsonObject(text);
        validateJsonObject(text, o);

        return o;
    }

    protected void validateResponseParser(ResponseParser<M> p) throws TreasureDataClientException {
        if (!(p instanceof JsonResponseParser)) {
            TreasureDataClientException e = new TreasureDataClientException(
                    "Internal Error: unexpected response parser is used: " + p);
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    protected void validateJsonText(String text) throws TreasureDataClientException {
        if (text == null) {
            TreasureDataClientException e = new TreasureDataClientException(
                    "Cannot create model object from JSON text: " + text);
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    protected Object getJsonObject(String text) throws TreasureDataClientException {
        return JSONValue.parse(text);
    }

    protected void validateJsonObject(String text, Object o)
            throws TreasureDataClientException {
        if (o == null) {
            TreasureDataClientException e = new TreasureDataClientException(
                    "Cannot unmarshall from JSON text: " + text);
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }
}
