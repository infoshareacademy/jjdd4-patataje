package com.hydrozagadka.servlets;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addSomeData")
public class AddSomeData extends HttpServlet {

    private CSVLoader csvLoader = new CSVLoader();
    private FilterFiles filterFiles = new FilterFiles(csvLoader);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer containerId = Integer.parseInt(request.getParameter("containerId"));

        filterFiles.getWaterContainerByID(containerId);

    }
}
