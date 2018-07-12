package com.hydrozagadka.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loadservlet")
public class LoadServlet extends HttpServlet {
    private final String DestinationDirpath = "/home/pawelorlikowski/jjdd4-patataje/HydroZagadkaApp/data/data";
    private final String d= "/home/pawelorlikowski/Downloads";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String s = request.getParameter("file");
            PrintWriter pr=response.getWriter();
            pr.print(s);
            pr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
