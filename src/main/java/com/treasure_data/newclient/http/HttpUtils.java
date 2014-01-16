package com.treasure_data.newclient.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.treasure_data.newclient.Request;

public class HttpUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public static String encodeParameters(Request<?> request) {
        List<NameValuePair> nameValuePairs = null;
        if (request.getParameters().size() > 0) {
            nameValuePairs = new ArrayList<NameValuePair>(request.getParameters().size());
            for (Entry<String, String> entry : request.getParameters().entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        String encodedParams = null;
        if (nameValuePairs != null) {
            encodedParams = URLEncodedUtils.format(nameValuePairs, DEFAULT_ENCODING);
        }

        return encodedParams;
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
