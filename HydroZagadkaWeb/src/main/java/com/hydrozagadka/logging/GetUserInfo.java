package com.hydrozagadka.logging;

import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;

public class GetUserInfo implements Runnable {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private AsyncContext asyncContext;

    public GetUserInfo(HttpServletRequest request, HttpServletResponse response, AsyncContext asyncContext) {
        this.request = request;
        this.response = response;
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        HttpSession session = request.getSession();
        OAuthService service = (OAuthService) session.getAttribute("oauth2Service");

        String code = request.getParameter("code");
        Token token = service.getAccessToken(null, new Verifier(code));
        session.setAttribute("token", token);

        try {
            request.login("fred", "fredfred");
        } catch (ServletException e) {
            e.getRootCause();
        }

        OAuthRequest oReq = new OAuthRequest(Verb.GET,  "https://www.googleapis.com/oauth2/v2/userinfo&quot");
        service.signRequest(token, oReq);
        Response oResp = oReq.send();
        JsonReader reader = Json.createReader(new ByteArrayInputStream(oResp.getBody().getBytes()));
        JsonObject profile = reader.readObject();
        session.setAttribute("name", profile.getString("name"));
        session.setAttribute("email", profile.getString("email"));
        asyncContext.complete();
    }

}
