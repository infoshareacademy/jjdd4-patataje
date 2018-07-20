package com.hydrozagadka.servlets;

import com.hydrozagadka.dao.HistoryDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/check")
public class CheckingIntervals extends HttpServlet {

    @Inject
    private HistoryDao historyDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate start = LocalDate.of(2016, 01, 04);
        LocalDate end = LocalDate.of(2016, 12, 04);

        resp.getWriter().println(historyDao.findWaterdeepBetweenTwoDates(start, end, 153190100L));
    }
}
