package com.hydrozagadka.Beans;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NewestHistoryDataLoadBeanTest {

    @InjectMocks
    private NewestHistoryDataLoadBean testObj;
    @Mock
    private HistoryDao historyDao;
    @Mock
    private WaterContainerDao waterContainerDao;


    @Test
    public void loadNewestHistoryToDatabase() {
        //given
        NewestWaterContainerData newestWaterContainerData = new NewestWaterContainerData();
        newestWaterContainerData.setWaterLevelDate(null);
        newestWaterContainerData.setWaterTemperature(null);

        History history = new History();
        history.setDate(LocalDate.now());
        history.setTemperature(0.0);
        history.setFlow(0.0);

        // when
        testObj.loadNewestHistoryToDatabase(Collections.singletonList(newestWaterContainerData));

        // then
        Mockito.verify(historyDao).save(history);
    }

    @Test
    public void loadNewestHistoryToDatabaseWhenWaterLevelDateIsNotNull() {
        //given
        NewestWaterContainerData newestWaterContainerData = new NewestWaterContainerData();
        newestWaterContainerData.setWaterLevelDate(LocalDateTime.of(2016,02,16,16,15));
        newestWaterContainerData.setWaterTemperature(null);

        History history = new History();
        history.setDate(LocalDate.of(2016,02,16));
        history.setTemperature(0.0);
        history.setFlow(0.0);

        // when
        testObj.loadNewestHistoryToDatabase(Collections.singletonList(newestWaterContainerData));

        // then
        Mockito.verify(historyDao).save(history);

    }

    @Test
    public void loadNewestHistoryToDatabaseWhenWaterTemperatureIsNotNull() {
        //given
        NewestWaterContainerData newestWaterContainerData = new NewestWaterContainerData();
        newestWaterContainerData.setWaterLevelDate(null);
        newestWaterContainerData.setWaterTemperature(2.3);

        History history = new History();
        history.setDate(LocalDate.now());
        history.setTemperature(2.3);
        history.setFlow(0.0);

        // when
        testObj.loadNewestHistoryToDatabase(Collections.singletonList(newestWaterContainerData));

        // then
        Mockito.verify(historyDao).save(history);

    }
    @Test
    public void loadNewestHistoryToDatabaseWhenWaterLevelDateAndWaterTemperatureIsNotNull() {
        //given
        NewestWaterContainerData newestWaterContainerData = new NewestWaterContainerData();
        newestWaterContainerData.setWaterLevelDate(LocalDateTime.of(2016,02,15,16,15));
        newestWaterContainerData.setWaterTemperature(3.0);

        History history = new History();
        history.setDate(LocalDate.of(2016,02,15));
        history.setTemperature(3.0);
        history.setFlow(0.0);

        // when
        testObj.loadNewestHistoryToDatabase(Collections.singletonList(newestWaterContainerData));

        // then
        Mockito.verify(historyDao).save(history);

    }
}