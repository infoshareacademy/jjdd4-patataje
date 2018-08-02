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
        Map<String, Object> model = new HashMap<>();
        if (session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
        }
        if (session.getAttribute("isAdmin") == null) {
            session.setAttribute("isAdmin", false);
        }
        Boolean isAuth = (Boolean) session.getAttribute("isLoggedIn");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        model.put("isLoggedIn", isAuth ? (isAdmin ? "admin" : "user") : "none");

        template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());


        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template dosen't exist");
        }
    }
}
