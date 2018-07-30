package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.ApiConnector;
import com.hydrozagadka.Beans.NewestHistoryDataLoadBean;
import com.hydrozagadka.Beans.NewestWaterContainerDataLoadBean;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.UserDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/database")
public class DatabaseServlet extends HttpServlet {

    @Inject
    private WaterContainerDao waterContainerDao;
    @Inject
    private HistoryDao historyDao;
    @Inject
    private StatisticsDao statisticsDao;

    @Inject
    private NewestHistoryDataLoadBean newestHistoryDataLoadBean;
    @Inject
    private NewestWaterContainerDataLoadBean newestWaterContainerDataLoadBean;

    @Inject
    private ApiConnector apiConnector;

    @Inject
    private UserDao userDao;


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
        List<NewestWaterContainerData> imgwData = apiConnector.load();
        newestWaterContainerDataLoadBean.loadNewestWaterContainerToDatabase(imgwData);
        newestHistoryDataLoadBean.loadNewestHistoryToDatabase(imgwData);
        userDao.save(new User("orliktcz@gmail.com","asdasascr54t4grghfdaq3",false,0,new ArrayList<>()));
        userDao.save(new User("Admin@wp.pl", "fdfdsfdf",true,0,new ArrayList<>()));
        resp.sendRedirect("/welcome");
    }
}
