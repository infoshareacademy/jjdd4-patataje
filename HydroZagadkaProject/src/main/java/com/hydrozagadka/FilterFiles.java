package com.hydrozagadka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FilterFiles {

    private Map<Integer, WaterContainer> allFiles;
    private LoadFile loadFile;

    public FilterFiles(Map<Integer, WaterContainer> allFiles) {
        this.allFiles = allFiles;
    }


//    public List<Double> minAndMaxValueOfHistoryFiles(int id) {
//        List<Double> result = new ArrayList<>();
//        List<History> historyList = allFiles.get(id).getHistory();
//        result.add(historyList.stream()
//                .mapToDouble(History::getWaterDeep)
//                .max().getAsDouble());
//
//        result.add(historyList.stream()
//                .mapToDouble(History::getWaterDeep)
//                .min().getAsDouble());
//
//        return result;
//    }

    public void minAndMaxValueOfHistoryWithIntervals(LocalDate start, LocalDate end) {
        start = LocalDate.now();
        end = start.plusMonths(1);
        while (!start.isAfter(end)) {
            System.out.println(start);
            start = start.plusDays(1);
        }

    }

    public List<WaterContainer> filterThroughContainer(String nameOfWaterContainer) {
       List<WaterContainer> waterContainerList = new ArrayList<>();

        for (WaterContainer waterContainer : allFiles.values()) {
            if (waterContainer.getContainerName().equals(nameOfWaterContainer)) {
                waterContainerList.add(waterContainer);
            }
        }
        return waterContainerList;
    }

    public List<WaterContainer> showWaterContainers(String value) {

        List<WaterContainer> showContainers = new ArrayList<>();

        for (WaterContainer wt : allFiles.values()) {
            if (wt.getProvince().equals(value)) {
                showContainers.add(wt);
//                System.out.println(wt.getContainerName());
            }
        }
        return showContainers;
    }

}