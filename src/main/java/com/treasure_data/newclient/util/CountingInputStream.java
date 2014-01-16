package com.treasure_data.newclient.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends FilterInputStream {
    private long byteCount = 0;

    public CountingInputStream(InputStream in) {
        super(in);
    }

    public long getByteCount() {
        return byteCount;
    }

    @Override
    public int read() throws IOException {
        int tmp = super.read();
        byteCount += tmp >= 0 ? 1 : 0;
        return tmp;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int tmp = super.read(b, off, len);
        byteCount += tmp >= 0 ? tmp : 0;
        return tmp;
    }
}
