package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.UnzipDao;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.WaterContainer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet("/loadservlet")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    @Inject
    private UnzipDao unzipDao;

    private Map<Long, WaterContainer> waterContainerMap;
    public static final String DIRECT_PATH = "/home/orzel/jjdd4-patataje/HydroZagadkaApp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        PrintWriter pr = response.getWriter();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.contains(".zip")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pr.close();
            return;
        }
        InputStream is = filePart.getInputStream();
        unzipDao.unzip(is, DIRECT_PATH);
        CSVLoader csvLoader = new CSVLoader();
        waterContainerMap = csvLoader.getAllContainers();
        response.sendRedirect("/database");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
}
