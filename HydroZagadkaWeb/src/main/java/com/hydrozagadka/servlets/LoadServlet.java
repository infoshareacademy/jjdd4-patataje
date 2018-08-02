package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.DatabaseLoadBean;
import com.hydrozagadka.Beans.LoadZipToDatabaseBean;
import com.hydrozagadka.Beans.UnzipDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.WaterContainer;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.Map;

@WebServlet("/loadservlet")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoadServlet.class);

    @Inject
    private UnzipDao unzipDao;

    @Inject
    private FreeMarkerConfig freeMarkerConfig;

    @Inject
    private LoadZipToDatabaseBean loadZipToDatabaseBean;
    @Inject
    private DatabaseLoadBean databaseLoadBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        PrintWriter pr = response.getWriter();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.contains(".zip")) {
            logger.warn("No zip file found");

            throw new FileNotFoundException("zły format pliku");

        }
        InputStream is = filePart.getInputStream();
        loadZipToDatabaseBean.unzipFile(is);
        databaseLoadBean.loadWaterContainer();
        databaseLoadBean.loadHistory();
        databaseLoadBean.loadDataFromApi();
        response.sendRedirect("/welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

}
