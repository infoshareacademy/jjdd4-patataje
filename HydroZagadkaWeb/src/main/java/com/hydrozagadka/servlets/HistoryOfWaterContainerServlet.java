package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.ChartHistory;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/history")
public class HistoryOfWaterContainerServlet extends HttpServlet {

    private CSVLoader csvLoader = new CSVLoader();
    private LocalDate startdate = LocalDate.of(1954,01,01);
    private LocalDate enddate = LocalDate.now();
@Inject
private HistoryDao historyDao;
@Inject
private StatisticsDao statisticsDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if(isCorrectDate(startDate,endDate)){
             startdate = LocalDate.parse(startDate);
             enddate = LocalDate.parse(endDate);
             if(!isStartDateErlier(startdate,enddate)){
                 startdate = LocalDate.of(1954,01,01);
                 enddate = LocalDate.now();
             }
        }
        Long idWaterContainer = Long.parseLong(request.getParameter("station"));
        statisticsDao.update(idWaterContainer);
        List<ChartHistory> historyOfWaterContainer = historyDao.getHistoryByWaterContainer(idWaterContainer,startdate,enddate);
        ObjectMapper objectMapper = new ObjectMapper();
        //   objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String historyJsonAsString = objectMapper.writeValueAsString(historyOfWaterContainer);
        PrintWriter pw = response.getWriter();

        pw.println(historyJsonAsString);
    }
    private boolean isCorrectDate(String startDate,String endDate){
        if(startDate==null || startDate.isEmpty()) {
            return false;
        }
        if(endDate==null || endDate.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isStartDateErlier(LocalDate startdate,LocalDate enddate){
        if(startdate.isAfter(enddate)){
            return false;
        }
        return true;
    }

}
