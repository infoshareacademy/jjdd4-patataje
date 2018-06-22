package com.hydrozagadka;

public class History {

    private Integer year;
    private Integer month;
    private Integer day;
    private Double waterDeep;
    private Double flow;
    private Double temperature;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Double getWaterDeep() {
        return waterDeep;
    }

    public void setWaterDeep(Double waterDeep) {
        this.waterDeep = waterDeep;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }


    public History(Integer year, Integer month, Integer day, Double waterDeep, Double flow, Double temperature) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
    }

//    public ShowHistory() {
//
//    }
}
