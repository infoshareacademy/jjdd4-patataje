package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.UnzipDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@WebServlet("/loadservlet")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoadServlet.class);

    @Inject
    private UnzipDao unzipDao;
    @Inject
    FreeMarkerConfig freeMarkerConfig;
    public static final String DIRECT_PATH = "/home/orzel/jjdd4-pat" +
            "ataje/HydroZagadkaApp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        PrintWriter pr = response.getWriter();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.contains(".zip")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pr.close();
            logger.warn("No zip file found");
            return;
        }

        InputStream is = filePart.getInputStream();
        unzipDao.unzip(is, DIRECT_PATH);
        logger.info("Unzip File: {}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
