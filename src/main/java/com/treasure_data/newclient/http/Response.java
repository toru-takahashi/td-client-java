package com.treasure_data.newclient.http;

import java.io.InputStream;

public interface Response {

    InputStream getContent();
    void setContent(InputStream in);
}
