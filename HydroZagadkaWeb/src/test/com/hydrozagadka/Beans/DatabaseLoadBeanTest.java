package com.hydrozagadka.Beans;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.WaterContainerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class DatabaseLoadBeanTest {

    @InjectMocks
    private DatabaseLoadBean testObj;
    @Mock
    private CSVLoader csvLoader;
    @Mock
    WaterContainerDao waterContainerDao;
    @Mock
    StatisticsDao statisticsDao;


    @Test
    public void loadWaterContainer() {
        //given
        HashMap<Long, WaterContainer> waterContainerMap = new HashMap<>();
        WaterContainer waterContainer = new WaterContainer();
        waterContainer.setId(1L);
        waterContainerMap.put(0L, waterContainer);

        Mockito.when(waterContainerDao.findById(1L)).thenReturn(null);
        Mockito.when(csvLoader.getAllContainers()).thenReturn(waterContainerMap);

        //when
        testObj.loadWaterContainer();
        //then
        Mockito.verify(waterContainerDao).save(waterContainer);
        Mockito.verify(statisticsDao).save(new Statistics(0L, waterContainer));
    }

    @Test
    public void loadHistory() {
    }

    @Test
    public void loadDataFromApi() {
    }
}