package com.treasure_data.newclient.model.gen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonResponseParser<M> extends AbstractResponseParser<M> {
    private static final Logger LOG = Logger.getLogger(JsonResponseParser.class.getName());

    private String jsonText = null;

    public JsonResponseParser() {
    }

    public String getJsonText() {
        return jsonText;
    }

    @Override
    public void parse(ResponseModelGen<M> init, InputStream in)
            throws IOException {
        StringBuilder sbuf = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in)); 
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sbuf.append(line);
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                }
            }
        }

        jsonText = sbuf.toString();
        LOG.log(Level.FINE, "json text: " + jsonText);
    }
}
