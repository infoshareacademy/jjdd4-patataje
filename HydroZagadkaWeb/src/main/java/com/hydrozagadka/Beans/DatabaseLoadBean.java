package com.hydrozagadka.Beans;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Stateless
public class DatabaseLoadBean {

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

    private Map<Long, WaterContainer> waterContainerMap;

    private CSVLoader csvLoader = new CSVLoader();


    public void loadWaterContainer() throws IOException {
        waterContainerMap = csvLoader.loadCSV();
        waterContainerMap.values()
                .forEach(waterContainer -> {
                    if (waterContainerDao.findById(waterContainer.getId()) == null) {
                        waterContainerDao.save(waterContainer);
                        statisticsDao.save(new Statistics(0L, waterContainer));
                    }
                });
        loadHistory(waterContainerMap);
    }

    public void loadHistory(Map<Long, WaterContainer> data) throws IOException {
        data.values()
                .forEach(waterContainer -> waterContainer.getHistory()
                        .forEach(history -> {
                            Long wcId = history.getContainerId();
                            if (historyDao.findByDate(history.getDate(), wcId).size() == 0) {
                                WaterContainer wc = waterContainerDao.findById(wcId);
                                history.setWaterContainers(wc);
                                historyDao.save(history);
                            }
                        }));
        org.apache.commons.io.FileUtils.cleanDirectory(new File("/opt/jboss/patataje-upload"));
    }

    public void loadDataFromApi() {

        List<NewestWaterContainerData> imgwData = apiConnector.load();
        newestWaterContainerDataLoadBean.loadNewestWaterContainerToDatabase(imgwData);
        newestHistoryDataLoadBean.loadNewestHistoryToDatabase(imgwData);
    }
}
