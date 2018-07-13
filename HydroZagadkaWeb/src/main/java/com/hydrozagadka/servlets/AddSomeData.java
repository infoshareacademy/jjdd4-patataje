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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer containerId = Integer.parseInt(req.getParameter("containerId"));

        filterFiles.getWaterContainerByID(containerId);
    }
}
