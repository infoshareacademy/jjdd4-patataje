package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.GoogleVerifierBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@WebServlet("/validation")
public class ValidationServlet extends HttpServlet {
    @Inject
    GoogleVerifierBean googleVerifierBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.getWriter().println(googleVerifierBean.verify(request.getParameter("idtoken")));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
