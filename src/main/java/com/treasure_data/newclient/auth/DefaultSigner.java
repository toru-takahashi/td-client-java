package com.treasure_data.newclient.auth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import com.treasure_data.newclient.Request;
import com.treasure_data.newclient.TreasureDataClientException;
import com.treasure_data.newclient.model.GetServerStatusRequest;
import com.treasure_data.newclient.model.TreasureDataServiceRequest;

public class DefaultSigner implements Signer {
    private static final Logger LOG = Logger.getLogger(DefaultSigner.class.getName());

    private static final Object lock = new Object();
    private static final SimpleDateFormat RFC2822;

    static {
        RFC2822 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    }

    private String toRFC2822(Date from) {
        synchronized(lock) {
            return RFC2822.format(from);
        }
    }

    @Override
    public <REQ extends TreasureDataServiceRequest> void sign(
            Request<REQ> request,
            TreasureDataCredentials credentials)
                    throws TreasureDataClientException {

        if (request.getOriginalRequest() instanceof GetServerStatusRequest) {
            LOG.fine("Signature is not needed for GetServerStatusRequest");
            return;
        }

        // TODO FIXME implements internal key TD2
        String apiKey = credentials.getApiKey();
        if (apiKey == null) {
            throw new TreasureDataClientException("No specified apikey");
        }
        request.addHeader("Authorization", "TD1 " + apiKey);
        request.addHeader("Date", toRFC2822(new Date()));
    }

}
