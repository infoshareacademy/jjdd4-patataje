package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.UnzipDao;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet("/loadservlet")
@MultipartConfig
public class LoadServlet extends HttpServlet {
    @Inject
    private WaterContainerDao waterContainerDao;
    @Inject
    private HistoryDao historyDao;
    @Inject
    private UnzipDao unzipDao;
    @Inject
    private StatisticsDao statisticsDao;
    private Map<Long,WaterContainer> waterContainerMap;
    public static final String DIRECT_PATH = "/home/orzel/jjdd4-patataje/HydroZagadkaApp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        PrintWriter pr = response.getWriter();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.contains(".zip")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pr.close();
            return;
        }
        InputStream is = filePart.getInputStream();
        unzipDao.unzip(is, DIRECT_PATH);
        CSVLoader csvLoader = new CSVLoader();
        waterContainerMap = csvLoader.getAllContainers();
        updateHistory();
        updateWaterContainer();
        response.sendRedirect("/welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    private void updateWaterContainer(){
        waterContainerMap.values().stream()
                .forEach(waterContainer -> {
                    if (waterContainerDao.findById(waterContainer.getId()) == null){
                        waterContainerDao.save(waterContainer);
                        statisticsDao.save(new Statistics(0L, waterContainer));
                    }
                });
    }

    public void updateHistory(){
        waterContainerMap.values().stream()
                .forEach(waterContainer -> waterContainer.getHistory().stream()
                        .forEach(history -> {
                            Long wcId = history.getContainerId();
                            if(historyDao.findByDate(history.getDate(),wcId).size()==0) {
                                WaterContainer wc = waterContainerDao.findById(wcId);
                                history.setWaterContainers(wc);
                                historyDao.save(history);
                            }
                        }));
    }
}
