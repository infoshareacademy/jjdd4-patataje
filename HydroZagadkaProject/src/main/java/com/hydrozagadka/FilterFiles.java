package com.hydrozagadka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FilterFiles {
    private History historyFiles;
    private LoadFile loadFile;
    private Map<Integer, WaterContainer> allFiles;

    public FilterFiles(Map<Integer, WaterContainer> allFiles) {
        this.allFiles = allFiles;
    }


    public List<Double> minValueOfHistoryFiles() {
        int id = 149180010;
        Double min = Double.MAX_VALUE;
        Double max = Double.MIN_VALUE;
        List<Double> listOfMaxAndMinValues = new ArrayList<>();
        for (History history : allFiles.get(id).getHistory()) {
            if (min > history.getWaterDeep()) {
                min = history.getWaterDeep();
            }
            if (max < history.getWaterDeep()){
                max = history.getWaterDeep();
            }
        }
        listOfMaxAndMinValues.add(max);
        listOfMaxAndMinValues.add(min);
        return listOfMaxAndMinValues;
    }

    public List<String> showWaterContainers(String value) {

        System.out.println(value);
        List<String> showContainers = new ArrayList<>();
        for (WaterContainer wt : allFiles.values()) {
            if (wt.getProvince().equals(value)) {
                showContainers.add(wt.getContainerName());

//                System.out.println(wt.getContainerName());
            }
        }
        System.out.println(showContainers);
        return showContainers;
    }
}