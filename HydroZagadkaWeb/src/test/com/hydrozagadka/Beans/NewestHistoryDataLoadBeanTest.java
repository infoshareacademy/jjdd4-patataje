package com.hydrozagadka.Beans;

import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class NewestHistoryDataLoadBeanTest {

    @InjectMocks
    private NewestHistoryDataLoadBean testObj;
    @Mock
    private HistoryDao statisticsDao;
    @Mock
    private WaterContainerDao waterContainerDao;



    @Test
    public void loadNewestHistoryToDatabase() {
        //given




    }
}