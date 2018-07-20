package com.hydrozagadka.servlets;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.History;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
                        .forEach(history -> {
                            Long wcId = history.getContainerId();
                            WaterContainer wc = waterContainerDao.findById(wcId);
                            history.setWaterContainers(wc);
                            historyDao.save(history);
                        }));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
