package com.treasure_data.newclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.treasure_data.newclient.Request;

public class RepeatableInputStreamRequestEntity extends BasicHttpEntity {
    private static final Logger LOG = Logger.getLogger(RepeatableInputStreamRequestEntity.class.getName());

    private boolean firstAttempt = true;
    private InputStreamEntity inputStreamRequestEntity;
    private InputStream content;
    private IOException originalException;

    public RepeatableInputStreamRequestEntity(Request<?> request) {
        setChunked(false);

        long contentLength = -1;
        try {
            String contentLengthString = request.getHeaders().get("Content-Length");
            if (contentLengthString != null) {
                contentLength = Long.parseLong(contentLengthString);
            }
        } catch (NumberFormatException nfe) {
            LOG.warning("Unable to parse content length from request.  " +
                    "Buffering contents in memory.");
        }

        String contentType = request.getHeaders().get("Content-Type");

        inputStreamRequestEntity = new InputStreamEntity(request.getContent(), contentLength);
        inputStreamRequestEntity.setContentType(contentType);
        content = request.getContent();

        setContent(content);
        setContentType(contentType);
        setContentLength(contentLength);
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public boolean isRepeatable() {
        return content.markSupported() || inputStreamRequestEntity.isRepeatable();
    }

    @Override
    public void writeTo(OutputStream output) throws IOException {
        try {
            if (!firstAttempt && isRepeatable()) content.reset();

            firstAttempt = false;
            inputStreamRequestEntity.writeTo(output);
        } catch (IOException ioe) {
            if (originalException == null) originalException = ioe;
            throw originalException;
        }
    }
}
