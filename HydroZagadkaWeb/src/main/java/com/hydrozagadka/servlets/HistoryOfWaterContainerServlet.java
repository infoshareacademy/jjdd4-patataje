package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;
import com.hydrozagadka.History;
import com.hydrozagadka.Model.WaterContainerView;
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
import java.util.List;
import java.util.Map;

@WebServlet("/history")
public class HistoryOfWaterContainerServlet extends HttpServlet {
    CSVLoader csvLoader = new CSVLoader();
    FilterFiles filterFiles = new FilterFiles(csvLoader);
    @Inject
    FreeMarkerConfig freeMarkerConfig;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idWaterContainer = Integer.parseInt(request.getParameter("station"));
        Map<String, Object> model = new HashMap<>();
        List<History> historyOfWaterContainer = filterFiles
                .getWaterContainerByID(idWaterContainer)
                .getHistory();
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String historyJsonAsString = objectMapper.writeValueAsString(historyOfWaterContainer);
        model.put("history", historyJsonAsString);
        Template template = freeMarkerConfig.getTemplate("chart", getServletContext());
        try {
            template.process(model, response.getWriter());
        } catch (TemplateException e) {
        }
    }
}
