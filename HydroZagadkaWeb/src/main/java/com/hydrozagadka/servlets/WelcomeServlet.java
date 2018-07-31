package com.hydrozagadka.servlets;

import com.hydrozagadka.User;
import com.hydrozagadka.dao.UserDao;
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


@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(WelcomeServlet.class);
    @Inject
    private FreeMarkerConfig freeMarkerConfig;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Template template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());
        Map<String, Object> model = new HashMap<>();
        //probny użytkownik
        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Szablon nie istnieje", e);
        }
    }
}
