package com.treasure_data.newclient.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.AbortableHttpRequest;

public class HttpMethodReleaseInputStream extends InputStream {
    private static final Logger LOG = Logger.getLogger(HttpMethodReleaseInputStream.class.getName());

    private InputStream in = null;
    private HttpEntityEnclosingRequest httpRequest = null;
    private boolean alreadyReleased = false;
    private boolean underlyingStreamConsumed = false;

    public HttpMethodReleaseInputStream(HttpEntityEnclosingRequest httpMethod) {
        this.httpRequest = httpMethod;

        try {
            in = httpMethod.getEntity().getContent();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Unable to obtain HttpMethod's response data stream", e);
            try {
                httpMethod.getEntity().getContent().close();
            } catch (Exception ex) {
                // ignore
            }
            in = new ByteArrayInputStream(new byte[] {}); // Empty input stream;
        }
    }

    public HttpEntityEnclosingRequest getHttpRequest() {
        return httpRequest;
    }

    @Override
    public int read() throws IOException {
        try {
            int read = in.read();
            if (read == -1) {
                underlyingStreamConsumed = true;
                if (!alreadyReleased) {
                    releaseConnection();
                    LOG.fine("Released HttpMethod as its response data stream is fully consumed");
                }
            }
            return read;
        } catch (IOException e) {
            releaseConnection();
            LOG.log(Level.FINE, "Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    protected void releaseConnection() throws IOException {
        if (!alreadyReleased) {
            if (!underlyingStreamConsumed) {
                // Underlying input stream has not been consumed, abort method
                // to force connection to be closed and cleaned-up.
                if (httpRequest instanceof AbortableHttpRequest) {
                    AbortableHttpRequest abortableHttpRequest = (AbortableHttpRequest)httpRequest;
                    abortableHttpRequest.abort();
                }
            }
            in.close();
            alreadyReleased = true;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        try {
            int read = in.read(b, off, len);
            if (read == -1) {
                underlyingStreamConsumed = true;
                if (!alreadyReleased) {
                    releaseConnection();
                    LOG.fine("Released HttpMethod as its response data stream is fully consumed");
                }
            }
            return read;
        } catch (IOException e) {
            releaseConnection();
            LOG.log(Level.FINE, "Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    public int available() throws IOException {
        try {
            return in.available();
        } catch (IOException e) {
            releaseConnection();
            LOG.log(Level.FINE, "Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    public void close() throws IOException {
        if (!alreadyReleased) {
            releaseConnection();
            LOG.log(Level.FINE, "Released HttpMethod as its response data stream is closed");
        }
        in.close();
    }

    protected void finalize() throws Throwable {
        if (!alreadyReleased) {
            LOG.warning("Attempting to release HttpMethod in finalize() as its response data stream has gone out of scope. "
                + "This attempt will not always succeed and cannot be relied upon! Please ensure S3 response data streams are "
                + "always fully consumed or closed to avoid HTTP connection starvation.");
            releaseConnection();
            LOG.warning("Successfully released HttpMethod in finalize(). You were lucky this time... "
                + "Please ensure S3 response data streams are always fully consumed or closed.");
        }
        super.finalize();
    }
}
