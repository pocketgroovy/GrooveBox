package com.miyamoto.groovebox;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by yoshiloop on 6/7/16.
 */
public class OAuthHandler {

    private final String CONSUMER_KEY = "sWvz1k724X3WtLD9VNSqtz3TH";
    private final String CONSUMER_SECRET = "0MUtI9FOs7losWMuHDa2Ik5CWG9cfU1H4T92Ne83voAjfT0ibJ";

    private final String BEARER_CREDENTIALS = CONSUMER_KEY + ":" + CONSUMER_SECRET;

    private JSONObject token;

    public static final String OAUTH_TOKEN_URL = "https://api.twitter.com/oauth2/token";

    public static String getBearerCredentials() {

        return encodeBearerCredentials();
    }

    private static String encodeBearerCredentials() {
        return Base64.encodeToString(BEARER_CREDENTIALS.getBytes(), Base64.DEFAULT);
    }

    public String getAccessToken(String username) throws IOException, JSONException {
        NetworkController networkContoller = new NetworkController();
        token = networkContoller.postRequest(username, OAUTH_TOKEN_URL);
        if (token.has("token_type")) {
            if (token.get("token_type") == "bearer") {
                return token.get("access_token").toString();
            }
        }
        return null;
    }
}
