package com.hydrozagadka.servlets;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
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

    private Map<Long, WaterContainer> waterContainerMap;

    public static final String DIRECT_PATH = "../../../../../HydroZagadkaApp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        PrintWriter pr = response.getWriter();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.contains(".zip")) {
            logger.warn("No zip file found");

//            przekazać tu atrybut o błędzie
            response.setContentType("text/html;charset=UTF-8");
            Template template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());
            Map<String, Object> model = new HashMap<>();
            model.put("noZip", "Nie wysłałeś pliku .zip");

            response.sendRedirect("/welcome#upload");
            try {
                template.process(model, response.getWriter());
            } catch (TemplateException e) {
                logger.warn("Template doesn't exist");
            }
            return;
        }
        InputStream is = filePart.getInputStream();
        unzipDao.unzip(is, DIRECT_PATH);
        logger.info("Unzip File: {}");
        CSVLoader csvLoader = new CSVLoader();
        waterContainerMap = csvLoader.getAllContainers();
        response.sendRedirect("/database");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

}
