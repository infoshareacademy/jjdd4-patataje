package com.hydrozagadka.servlets;

import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(com.hydrozagadka.servlets.WelcomeServlet.class);
    @Inject
    private FreeMarkerConfig freeMarkerConfig;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");


        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");

        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Nieznany";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Nieznany";
        }

        logger.warn("Error occured, from servlet: " + servletName + ", see details: " + " error code: " + statusCode + " ,throwable: " + throwable);

        response.setContentType("text/html;charset=UTF-8");
        Template template = freeMarkerConfig.getTemplate("mainPartsOfPage/errorsPage.ftlh", getServletContext());


        Map<String, Object> model = new HashMap<>();
        model.put("statusCode", statusCode);
        model.put("servletName", servletName);
        model.put("throwable", throwable);
        model.put("requestUri", requestUri);


        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template doesn't exist");
        }


    }


}
