package com.treasure_data.newclient.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map.Entry;

public class HttpUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";

    public static String encodeParameters(Request<?> request) {
        final StringBuilder result = new StringBuilder();

        for (Entry<String, String> entry : request.getParameters().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            final String encodedName = encode(key);
            final String encodedValue = value != null ? encode(value) : "";

            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            result.append(encodedName);
            result.append(NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }

        return result.toString();
    }

    private static String encode (final String content) {
        try {
            return URLEncoder.encode(content, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    public static boolean isUsingNonDefaultPort(URI uri) {
        String scheme = uri.getScheme().toLowerCase();
        int port = uri.getPort();

        if (port <= 0) {
            return false;
        }

        if (scheme.equals("http") && port == 80) {
            return false;
        }

        if (scheme.equals("https") && port == 443) {
            return false;
        }

        return true;
    }
}
