package com.hydrozagadka.Beans;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class NewestHistoryDataLoadBean {

    @Inject
    private HistoryDao historyDao;
    @Inject
    private WaterContainerDao waterContainerDao;

    private Logger logger = LoggerFactory.getLogger(NewestHistoryDataLoadBean.class);

    public void loadNewestHistoryToDatabase(List<NewestWaterContainerData> apiData) {
        apiData.forEach(s -> createHistoryData(s));
    }

    private void createHistoryData(NewestWaterContainerData s) {
        Double temperature;

        if (s.getWaterTemperature() == null) {
            temperature = 0.0;
            logger.warn("Brak danych o temperaturze. Temperatura ustawiona na 0");
        } else {
            temperature = s.getWaterTemperature();
        }
        historyDao.save(new History(
                s.getWaterLevelDate(),
                s.getWaterLevel(),
                0.0,
                temperature,
                s.getWaterTemperatureDate(),
                s.getIcePhenomenonDate(),
                s.getIcePhenomenon(),
                s.getIcePhenomenonDate(),
                s.getOvergrowthPhenomenon(),
                s.getOvergrowthPhenomenonDate(),
                waterContainerDao.findById(s.getId()),
                s.getId()));
    }
}
