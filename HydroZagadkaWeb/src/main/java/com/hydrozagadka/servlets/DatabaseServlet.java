package com.hydrozagadka.servlets;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/database")
public class DatabaseServlet extends HttpServlet {

    @Inject
    private WaterContainerDao waterContainerDao;
    @Inject
    private HistoryDao historyDao;
    @Inject
    private StatisticsDao statisticsDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSVLoader csvLoader = new CSVLoader();
        Map<Long, WaterContainer> waterContainerMap = csvLoader.getAllContainers();

        waterContainerMap.values().stream()
                .forEach(waterContainer -> {
                    if (waterContainerDao.findById(waterContainer.getId()) == null) {
                        waterContainerDao.save(waterContainer);
                        statisticsDao.save(new Statistics(0L, waterContainer));
                    }
                });

        waterContainerMap.values().stream()
                .forEach(waterContainer -> waterContainer.getHistory().stream()
                        .forEach(history -> {
                            Long wcId = history.getContainerId();
                            if (historyDao.findByDate(history.getDate(), wcId).size() == 0) {
                                WaterContainer wc = waterContainerDao.findById(wcId);
                                history.setWaterContainers(wc);
                                historyDao.save(history);
                            }
                        }));
        resp.sendRedirect("/welcome");
    }
}
