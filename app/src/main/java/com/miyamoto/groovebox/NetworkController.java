package com.miyamoto.groovebox;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by yoshiloop on 6/7/16.
 */
public class NetworkController {
    private HttpClient client;


    public NetworkController(){

        client = new DefaultHttpClient();
    }

    public JSONObject postRequest(String username, String URL) throws IOException, JSONException
    {
        StringBuilder url = new StringBuilder(URL);
        url.append(username);
        HttpPost post = bearerTokenRequest(url);

        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        if(status == 200){
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity);
            JSONObject jsonData = new JSONObject(data);
            return jsonData;
        }else{
           throw new RuntimeException("Network Error");
        }
    }

    private HttpPost bearerTokenRequest(StringBuilder url) throws UnsupportedEncodingException {
        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("grant_type", "client_credentials"));
        HttpPost post = new HttpPost(url.toString());
        post.addHeader("Authorization", "Basic " + OAuthHandler.getBearerCredentials());
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        post.setEntity(new UrlEncodedFormEntity(postParams));
        return post;
    }

}
