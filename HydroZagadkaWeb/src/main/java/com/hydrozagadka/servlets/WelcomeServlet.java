package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.ApiConnector;
import com.hydrozagadka.Beans.NewestHistoryDataLoadBean;
import com.hydrozagadka.Beans.NewestWaterContainerDataLoadBean;
import com.hydrozagadka.Model.NewestWaterContainerData;
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
import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(WelcomeServlet.class);
    @Inject
    private FreeMarkerConfig freeMarkerConfig;
    @Inject
    private ApiConnector apiConnector;
    @Inject
    private NewestHistoryDataLoadBean newestHistoryDataLoadBean;
    @Inject
    NewestWaterContainerDataLoadBean newestWaterContainerDataLoadBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NewestWaterContainerData> imgwData = apiConnector.load();
        response.setContentType("text/html;charset=UTF-8");
        Template template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());
        newestWaterContainerDataLoadBean.loadNewestWaterContainerToDatabase(imgwData);
        newestHistoryDataLoadBean.loadNewestHistoryToDatabase(imgwData);
        Map<String, Object> model = new HashMap<>();

        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
            logger.warn("Template dosen't exist");
        }
    }
}
