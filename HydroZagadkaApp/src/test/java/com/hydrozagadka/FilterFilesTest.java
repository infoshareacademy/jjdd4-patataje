package com.hydrozagadka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterFilesTest {

    private FilterFiles subject;
    private Map<Integer, WaterContainer> testData;

    @Mock
    private CSVLoader csvLoaderMock;



    @Before
    public void setup() {
        List<History> testHistoryList = new ArrayList<>();
        testHistoryList.add(new History(LocalDate.now(), 1d, 2d, 3d));
        testHistoryList.add(new History(LocalDate.now(), 10d, 2d, 3d));
        testData = new HashMap<>();
        testData.put(0, new WaterContainer(0, "aaa", "aassdd", "pomorskie", testHistoryList));
        testData.put(1, new WaterContainer(1, "aaa", "aassdd", "kujawsko-4pomorskie", testHistoryList));

        when(csvLoaderMock.getAllContainers()).thenReturn(testData);

        subject = new FilterFiles(csvLoaderMock);
    }

    @Test
    public void minAndMaxValueOfHistoryWaterDeeps() {
        // given
        History expectedHistory = new History(LocalDate.now(), 10d, 2d, 3d);

        // when
        List<History> actual = subject.minAndMaxValueOfHistoryWaterDeeps(0);

        // then
        assertTrue(actual.size() == 2);
        assertTrue(actual.contains(expectedHistory));
    }

    @Test
    public void filterThroughContainer() {
        // given
        // when
        // then
    }

    @Test
    public void showWaterContainersThroughProvince() {
        // given
        // when
        // then
    }

    @Test
    public void minAndMaxValueOfHistoryWaterDeeps1() {
        // given
        // when
        // then
    }

    @Test
    public void getWaterContainerByID() {
        // given
        // when
        // then
    }
}