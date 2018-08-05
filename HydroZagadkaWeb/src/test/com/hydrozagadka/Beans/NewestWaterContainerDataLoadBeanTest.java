package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.NewestWaterContainerData;
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

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NewestWaterContainerDataLoadBeanTest {

    @InjectMocks
    private NewestWaterContainerDataLoadBean testObj;
    @Mock
    private StatisticsDao statisticsDao;
    @Mock
    private WaterContainerDao waterContainerDao;


    @Test
    public void loadNewestWaterContainerToDatabase() {
        // given
        NewestWaterContainerData givenContainer = new NewestWaterContainerData();
        givenContainer.setId(2L);
        givenContainer.setContainer("Container");
        givenContainer.setStation("Station");
        givenContainer.setProvince("Province");

        WaterContainer expectedWaterContainer = new WaterContainer(2L, "Container", "Station", "Province", Collections.emptyList());

        Mockito.when(waterContainerDao.findById(2L)).thenReturn(null);

        // when
        testObj.loadNewestWaterContainerToDatabase(Collections.singletonList(givenContainer));

        // then
        Mockito.verify(waterContainerDao).save(expectedWaterContainer);
        Mockito.verify(statisticsDao).save(new Statistics(0L, expectedWaterContainer));
    }
    @Test
    public void loadNewestWaterContainerToDatabaseNotNull() {
        //given
        NewestWaterContainerData givenContainer = new NewestWaterContainerData();
        givenContainer.setId(2L);
        givenContainer.setContainer("Container");
        givenContainer.setStation("Station");
        givenContainer.setProvince("Province");

        Mockito.when(waterContainerDao.findById(2L)).thenReturn(new WaterContainer());

        //when
        testObj.loadNewestWaterContainerToDatabase(Collections.singletonList(givenContainer));

        //then
        Mockito.verify(statisticsDao, Mockito.never()).save(Mockito.any());
        Mockito.verify(waterContainerDao, Mockito.never()).save(Mockito.any());









    }
}