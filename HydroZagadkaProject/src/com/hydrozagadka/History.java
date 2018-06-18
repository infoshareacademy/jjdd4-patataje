package com.hydrozagadka;

public class History {

    private Integer year;
    private Integer month;
    private Integer day;
     Double waterDeep;
    private Double flow;
    private Double temperature;
    private WaterContainer waterContainer;

    public History(Integer year, Integer month, Integer day, Double waterDeep, Double flow, Double temperature, WaterContainer wc) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
        this.waterContainer = wc;
    }


}
