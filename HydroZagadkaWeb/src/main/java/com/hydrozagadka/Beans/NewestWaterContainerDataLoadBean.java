package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class NewestWaterContainerDataLoadBean {

    @Inject
    WaterContainerDao waterContainerDao;
    @Inject
    StatisticsDao statisticsDao;

    public void loadNewestWaterContainerToDatabase(List<NewestWaterContainerData> apiData) {
        apiData.forEach(s -> {
            if (waterContainerDao.findById(s.getId()) == null) {
                WaterContainer wt = new WaterContainer(
                        s.getId(),
                        s.getContainer(),
                        s.getStation(),
                        s.getProvince(),
                        new ArrayList<>());
                waterContainerDao.save(wt);
                statisticsDao.save(new Statistics(0L, wt));
            }
        });
    }
}
