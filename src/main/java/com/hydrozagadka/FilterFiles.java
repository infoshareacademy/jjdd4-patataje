package com.hydrozagadka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterFiles {
    private History historyFiles;
    private CSVLoader loadFile;
    private Map<Integer, WaterContainer> allFiles;

    public FilterFiles(CSVLoader loadFile) {
        this.loadFile = loadFile;
        this.allFiles = loadFile.load();
    }

    public List<WaterContainer> getWaterContainers(String province) {
        List<WaterContainer> getWaterContainer = new ArrayList<>();
        for (WaterContainer wt : allFiles.values()) {
            if (wt.getProvince().equals(province)) {
                getWaterContainer.add(wt);
            }
        }
        return getWaterContainer;
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
            if (waterContainer.getStationName().contains(nameOfWaterContainer)) {
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

    public WaterContainer getWaterContainerByID(Integer id) {
        return allFiles.get(id);
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