package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.GoogleVerifierBean;
import com.hydrozagadka.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/validation")
public class ValidationServlet extends HttpServlet {

    @Inject
    private GoogleVerifierBean googleVerifierBean;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = googleVerifierBean.verify(req.getParameter("idtoken"));
            HttpSession session = req.getSession();
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("isAdmin", user.isAdminaaa());
            session.setAttribute("token",user.getToken());

            Boolean isAuth = (Boolean) session.getAttribute("isLoggedIn");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAuth && isAdmin) {
                session.setAttribute("isAdmin",true);
            }

            req.getRequestDispatcher("/welcome").forward(req, resp);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
