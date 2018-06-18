package com.hydrozagadka;

import java.util.ArrayList;
import java.util.List;

public class WaterContainer {
     Integer id;
     String containerName;
     String stationName;
     String state;
     List<History> history = new ArrayList<>();

    public WaterContainer(Integer id, String containerName, String stationName, List<History> history) {
    this.id=id;
    this.containerName=containerName;
    this.stationName=stationName;
    this.history=history;

    }
}
