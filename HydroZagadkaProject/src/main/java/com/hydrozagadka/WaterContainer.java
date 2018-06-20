package com.hydrozagadka;

import java.util.ArrayList;
import java.util.List;

public class WaterContainer {
   private  Integer id;
     private String containerName;
     private String stationName;
     private String province;
     private List<History> history;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }


    public WaterContainer(Integer id, String containerName, String stationName, List<History> history) {
    this.id=id;
    this.containerName=containerName;
    this.stationName=stationName;
    this.history=history;

    }
}
