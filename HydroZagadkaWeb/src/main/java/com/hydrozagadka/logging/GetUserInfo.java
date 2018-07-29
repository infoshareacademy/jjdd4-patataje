package com.hydrozagadka.logging;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        }
    }
}
