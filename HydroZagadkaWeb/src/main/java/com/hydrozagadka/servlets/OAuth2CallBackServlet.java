package com.hydrozagadka.servlets;

import com.hydrozagadka.logging.GetUserInfo;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/oauth2callback", asyncSupported=true)
public class OAuth2CallBackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if (null != error && ("access_denied".equals(error.trim()))) {
            HttpSession sess = req.getSession();
            sess.invalidate();
            resp.sendRedirect(req.getContextPath());
            return;
        }

        AsyncContext ctx = req.startAsync();
        ctx.start(new GetUserInfo(req, resp, ctx));
    }
}
