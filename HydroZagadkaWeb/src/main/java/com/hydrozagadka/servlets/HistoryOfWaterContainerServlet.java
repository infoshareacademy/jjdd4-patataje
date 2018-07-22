package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.List;

@WebServlet("/history")
public class HistoryOfWaterContainerServlet extends HttpServlet {

    private LocalDate startDate = LocalDate.of(1954, 01, 01);
    private LocalDate endDate = LocalDate.now();
    @Inject
    private HistoryDao historyDao;
    @Inject
    private StatisticsDao statisticsDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if (isCorrectDate(startDate, endDate)) {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
            if (!isStartDateErlier(this.startDate, this.endDate)) {
                this.startDate = LocalDate.of(1954, 01, 01);
                this.endDate = LocalDate.now();
            }
        }
        Long idWaterContainer = Long.parseLong(request.getParameter("station"));
        statisticsDao.update(idWaterContainer);

        List<ChartHistory> historyOfWaterContainer = historyDao.getHistoryByWaterContainerWithDates(idWaterContainer, this.startDate, this.endDate);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String historyJsonAsString = objectMapper.writeValueAsString(historyOfWaterContainer);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.println(historyJsonAsString);
    }

    private boolean isCorrectDate(String startDate, String endDate) {
        if (startDate == null || startDate.isEmpty()) {
            return false;
        }
        if (endDate == null || endDate.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isStartDateErlier(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return true;
    }

}
