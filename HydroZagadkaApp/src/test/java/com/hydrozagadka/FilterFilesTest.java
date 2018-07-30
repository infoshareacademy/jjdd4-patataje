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
    private Map<Long, WaterContainer> testData;

    @Mock
    private CSVLoader csvLoaderMock;


    @Before
    public void setup() {
        List<History> testHistoryList = new ArrayList<>();
        testHistoryList.add(new History(LocalDate.now(), 1d, 2d, 3d));
        testHistoryList.add(new History(LocalDate.now(), 10d, 2d, 3d));
        testData = new HashMap<>();
        testData.put(0L, new WaterContainer(0L, "aaa", "aassdd", "pomorskie", testHistoryList));
        testData.put(1L, new WaterContainer(1L, "aaa", "aassdd", "kujawsko-4pomorskie", testHistoryList));

        when(csvLoaderMock.getAllContainers()).thenReturn(testData);

        subject = new FilterFiles(csvLoaderMock);
    }

    @Test
    public void minAndMaxValueOfHistoryWaterDeeps() {
        // given
        History expectedHistory = new History(LocalDate.now(), 10d, 2d, 3d);

        // when
        List<History> actual = subject.minAndMaxValueOfHistoryWaterDeeps(0L);

        // then
        assertTrue(actual.size() == 2);
        assertTrue(actual.contains(expectedHistory));
    }


}