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


}