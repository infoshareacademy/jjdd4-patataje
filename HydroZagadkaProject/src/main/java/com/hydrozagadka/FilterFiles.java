package com.hydrozagadka;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterFiles {

    private Map<Integer, WaterContainer> allFiles;


    public FilterFiles(Map<Integer, WaterContainer> allFiles) {
        this.allFiles = allFiles;
    }


    public List<Double> minAndMaxValueOfHistoryFiles(int id) {
        List<Double> result = new ArrayList<>();
        List<History> historyList = allFiles.get(id).getHistory();
        result.add(historyList.stream()
                .mapToDouble(History::getWaterDeep)
                .max().getAsDouble());

        result.add(historyList.stream()
                .mapToDouble(History::getWaterDeep)
                .min().getAsDouble());

        return result;
    }


    public List<WaterContainer> filterThroughContainer(String nameOfWaterContainer) {
        List<WaterContainer> waterContainerList = new ArrayList<>();

        for (WaterContainer waterContainer : allFiles.values()) {
            if (waterContainer.getStationName().equals(nameOfWaterContainer)) {
                waterContainerList.add(waterContainer);
            }
        }
        return waterContainerList;
    }

    public List<WaterContainer> showWaterContainersThroughProvince(String value) {

        List<WaterContainer> showContainers = new ArrayList<>();

        for (WaterContainer wt : allFiles.values()) {
            if (wt.getProvince().equals(value)) {
                showContainers.add(wt);
            }
        }
        return showContainers;
    }

    public List<Double> minAndMaxValueOfHistoryFiles(int id, LocalDate start, LocalDate end) {
        List<History> listOfWaterContainerHistory = allFiles.get(id).getHistory();
        List<Double> listOfResultValues = new ArrayList<>();


        listOfResultValues.add(listOfWaterContainerHistory.stream()
                .filter(x -> x.getDate().isAfter(start))
                .filter(x -> x.getDate().isBefore(end))
                .mapToDouble(History::getWaterDeep)
                .max().getAsDouble());

        listOfResultValues.add(listOfWaterContainerHistory.stream()
                .filter(x -> x.getDate().isAfter(start))
                .filter(x -> x.getDate().isBefore(end))
                .mapToDouble(History::getWaterDeep)
                .min().getAsDouble());


        return listOfResultValues;

    }

}