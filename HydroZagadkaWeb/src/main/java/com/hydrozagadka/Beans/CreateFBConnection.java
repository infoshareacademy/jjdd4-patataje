package com.hydrozagadka.Beans;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@ApplicationScoped
public class CreateFBConnection {
    private static final String APP_ID = "";
    private static final String APP_SECRET = "";
    private static final String REDIRECT_URL = "";

    private String token = "";

    public String getFBauthUrl() {
        String fbLoginUrl = "";
        try {
            fbLoginUrl = "http://www.facebook.com/dialog/oauth?"
                    + "client_id="
                    + CreateFBConnection.APP_ID
                    + "&redirect_uri="
                    + URLEncoder.encode((CreateFBConnection.REDIRECT_URL, "UTF-8") + "&scope=email";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fbLoginUrl;
    }

    public String getFBGraphUrl(String code) {
        String fbGraphUrl = "";

        try {
            fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id="
                    + CreateFBConnection.APP_ID
                    + "&redirect_uri="
                    + URLEncoder.encode(CreateFBConnection.REDIRECT_URL,
                    "UTF-8") + "&client_secret=" + APP_SECRET
                    + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fbGraphUrl;
    }

    public String getAccessToken(String code) {
        URL fbGraph;
        try {
            fbGraph = new URL(getFBGraphUrl(code));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Wrong code " + e);
        }

        URLConnection fbConnection;
        StringBuffer stringBuffer = null;

        try {
            fbConnection = fbGraph.openConnection();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
            String inputLine;
            stringBuffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't connect with facebook " + e);
        }

        token = stringBuffer.toString();
        if (token.startsWith("{")) {
            throw new RuntimeException("Access token invalid " + token);
        }

        return token;
    }
}
