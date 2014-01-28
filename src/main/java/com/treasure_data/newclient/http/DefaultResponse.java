package com.treasure_data.newclient.http;

import java.io.InputStream;

public class DefaultResponse implements Response {

    InputStream in;

    @Override
    public void setContent(InputStream in) {
        this.in = in;
    }

    @Override
    public InputStream getContent() {
        return in;
    }

}
