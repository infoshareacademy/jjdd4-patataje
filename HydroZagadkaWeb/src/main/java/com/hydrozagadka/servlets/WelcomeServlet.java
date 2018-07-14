package com.hydrozagadka.servlets;

import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class WelcomeServlet extends HttpServlet {
    @Inject
    FreeMarkerConfig freeMarkerConfig;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        Map<String,Object> model = new HashMap<>();
        Template template = freeMarkerConfig.getTemplate("index.ftlh",getServletContext());
        try {
            template.process(model,pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
