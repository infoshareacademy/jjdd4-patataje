package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.DatabaseLoadBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loadimgw")
public class LoadIMGWServlet extends HttpServlet {
    @Inject
    private DatabaseLoadBean databaseLoadBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        databaseLoadBean.loadDataFromApi();
        response.sendRedirect("/welcome");
    }
}
