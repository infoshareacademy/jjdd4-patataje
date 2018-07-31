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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(WelcomeServlet.class);
    @Inject
    private FreeMarkerConfig freeMarkerConfig;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template template;
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Boolean isAuth = (Boolean) session.getAttribute("isLoggedIn");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAuth && !isAdmin) {
            template = freeMarkerConfig.getTemplate("userPage.ftlh", getServletContext());
        } else if (isAuth && isAdmin) {
            template = freeMarkerConfig.getTemplate("adminPage.ftlh", getServletContext());
        } else {
            template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());
        }

        Map<String, Object> model = new HashMap<>();
        
        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template dosen't exist");
        }
    }
}
