package com.hydrozagadka;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a8.A8_Grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FilterFiles {
    private History historyFiles;
    private LoadFile loadFile;
    private Map<Integer, WaterContainer> allFiles;
    private Map<Integer, WaterContainer> provinces;

    public FilterFiles(LoadFile loadFile) {
        this.loadFile = loadFile;
        this.allFiles = loadFile.load();
    }


    public List<WaterContainer> getWaterContainers(String province) {
        System.out.println(province);
        List<WaterContainer> getWaterContainer = new ArrayList<>();
        for (WaterContainer wt : allFiles.values()) {
            if (wt.getProvince().equals(province)) {
                getWaterContainer.add(wt);
            }
        }
        return getWaterContainer;
    }

    public WaterContainer readExample(Integer id) {
        return allFiles.get(id);
    }

}