package com.hydrozagadka;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class FilterFiles {

    private Map<Integer, WaterContainer> waterContainerMap;
    private CSVLoader loadFile;


    public FilterFiles(CSVLoader loadFile) {
        this.loadFile = loadFile;
        this.waterContainerMap = loadFile.loadCSV();
    }


    public List<History> minAndMaxValueOfHistoryFiles(int id) {
        List<History> result = new ArrayList<>();
        List<History> historyList = waterContainerMap.get(id).getHistory();
        historyList.stream()
                .max(Comparator.comparingDouble(History::getWaterDeep))
                .ifPresent(result::add);

        historyList.stream()
                .min(Comparator.comparingDouble(History::getWaterDeep))
                .ifPresent(result::add);

        return result;
    }


    public List<WaterContainer> filterThroughContainer(String nameOfWaterContainer, String province) {

        return waterContainerMap.values().stream()
                .filter(value -> value.getStationName().contains(nameOfWaterContainer))
                .filter(value -> value.getProvince().equals(province))
                .collect(Collectors.toList());


    }

    public List<WaterContainer> showWaterContainersThroughProvince(String value) {

        return waterContainerMap.values().stream()
                .filter(elem -> elem.getProvince().equals(value))
                .collect(Collectors.toList());
    }

    public List<History> minAndMaxValueOfHistoryFiles(int id, LocalDate start, LocalDate end) {
        List<History> listOfResultValues = new ArrayList<>();
        List<History> listOfWaterContainerHistory = waterContainerMap.get(id).getHistory();

        listOfWaterContainerHistory.stream()
                .filter(x -> x.getDate().plusDays(1).isAfter(start))
                .filter(x -> x.getDate().minusDays(1).isBefore(end))
                .max(Comparator.comparingDouble(History::getWaterDeep))
                .ifPresent(listOfResultValues::add);


        listOfWaterContainerHistory.stream()
                .filter(x -> x.getDate().plusDays(1).isAfter(start))
                .filter(x -> x.getDate().minusDays(1).isBefore(end))
                .min(Comparator.comparingDouble(History::getWaterDeep))
                .ifPresent(listOfResultValues::add);


        return listOfResultValues;
    }

    public WaterContainer getWaterContainerByID(Integer id) {
        return waterContainerMap.get(id);
    }
}