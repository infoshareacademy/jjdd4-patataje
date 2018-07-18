package com.hydrozagadka.servlets;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Map;

@WebServlet(urlPatterns = "/database")
public class DatabaseServlet extends HttpServlet {

    @Inject
    private WaterContainerDao waterContainerDao;
    @Inject
    private HistoryDao historyDao;

    @Override
    public void init() throws ServletException {
        CSVLoader csvLoader = new CSVLoader();
        Map<Long,WaterContainer> waterContainerMap = csvLoader.getAllContainers();

        waterContainerMap.values().stream()
                .forEach(waterContainer -> waterContainerDao.save(waterContainer));

        waterContainerMap.values().stream()
                .forEach(waterContainer -> waterContainer.getHistory().stream()
                        .forEach(history -> historyDao.save(history)));

    }
}
