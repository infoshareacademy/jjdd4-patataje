package com.hydrozagadka;

import java.util.ArrayList;
import java.util.List;

public class WaterContainer {
   private  Integer id;
     private String containerName;
     private String stationName;
     private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    List<History> history = new ArrayList<>();

    public WaterContainer(Integer id, String containerName, String stationName, List<History> history) {
    this.id=id;
    this.containerName=containerName;
    this.stationName=stationName;
    this.history=history;

    }
}
