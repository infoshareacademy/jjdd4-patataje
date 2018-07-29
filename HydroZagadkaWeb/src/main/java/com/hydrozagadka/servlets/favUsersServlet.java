package com.hydrozagadka.servlets;

import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.dao.AdminStatsDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/usersFav")
public class favUsersServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(com.hydrozagadka.servlets.WelcomeServlet.class);
    @PersistenceContext
    EntityManager entityManager;
    @Inject
    private FreeMarkerConfig freeMarkerConfig;
    @Inject
    private Statistics statistics;
    @Inject
    private AdminStatsDao adminStatsDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");


        Template template = freeMarkerConfig.getTemplate("adminPage/statistics.ftlh", getServletContext());


//        Map<String, String> results = new HashMap<String, String>();
//
//        EntityManager em = entityManager;
//
//        // Construct and run query
//        String jpaQuery = "select User.email, User.token from User u";
//        List<Object[]> resultList = em.createQuery(jpaQuery).getResultList();
//
//        // Place results in map
//        for (Object[] borderTypes: resultList) {
//            results.put((String)borderTypes[0], (String)borderTypes[1]);
//        }

        Map<String, Object> model = new HashMap<>();
        model.put("user", adminStatsDao.getAllUsersList());
//

        //probny użytkownik
        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template dosen't exist");
        }
    }
}


