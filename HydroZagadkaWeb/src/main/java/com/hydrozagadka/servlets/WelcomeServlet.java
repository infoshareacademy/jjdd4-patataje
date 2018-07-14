package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.ProvinceBean;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/")
public class WelcomeServlet extends HttpServlet {
    @Inject
    FreeMarkerConfig freeMarkerConfig;
    @Inject
    ProvinceBean provinceBean;
    CSVLoader load = new CSVLoader();
    FilterFiles ff = new FilterFiles(load);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        Map<String,Object> model = new HashMap<>();
        model.put("provinces",provinceBean.getProvince());
        Template template = freeMarkerConfig.getTemplate("index.ftlh",getServletContext());
        try {
            template.process(model,pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        pr.close();
    }
}
