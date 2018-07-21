package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;
import com.hydrozagadka.History;
import com.hydrozagadka.Model.ChartHistory;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
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

    private CSVLoader csvLoader = new CSVLoader();
@Inject
private HistoryDao historyDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        Long idWaterContainer = Long.parseLong(request.getParameter("station"));
        List<ChartHistory> historyOfWaterContainer = historyDao.getHistoryByWaterContainer(idWaterContainer);
        ObjectMapper objectMapper = new ObjectMapper();
      //   objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String historyJsonAsString = objectMapper.writeValueAsString(historyOfWaterContainer);
        PrintWriter pw = response.getWriter();

        pw.println(historyJsonAsString);


    }
}
