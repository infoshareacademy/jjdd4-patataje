package com.hydrozagadka.servlets;

import com.hydrozagadka.logging.Google2Api;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.oauth.OAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/googleplus")
public class GooglePlusServ extends HttpServlet {

    private static final String clientId = "838946843173-a1982aq9rsq35t2hlanpjbn74ok93es0.apps.googleusercontent.com ";
    private static final String clientSecret = "FzlJXvQdAoSbvaqpTDh-JT7P ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceBuilder builder = new ServiceBuilder();
        OAuthService service = builder.provider(Google2Api.class)
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback("http://localhost:8080/welcome")
                .scope("openid profile email" + "https://www.googleapis.com/auth/plus.login " +
                "https://www.googleapis.com/auth/plus.me")
                .build();

        HttpSession session = req.getSession();
        session.setAttribute("oauth2Service", service);
        resp.sendRedirect(service.getAuthorizationUrl(null));
    }
}
