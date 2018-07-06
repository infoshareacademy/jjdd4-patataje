package com.hydrozagadka;

import java.time.LocalDate;

public class History {

    private LocalDate date;
    private Double waterDeep;
    private Double flow;
    private Double temperature;

    History(LocalDate date, Double waterDeep, Double flow, Double temperature) {
        this.date = date;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
    }

    LocalDate getDate() {
        return date;
    }

    Double getWaterDeep() {
        return waterDeep;
    }

    Double getFlow() {
        return flow;
    }

    Double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "History{" +
                "date=" + date +
                ", waterDeep=" + waterDeep +
                ", flow=" + flow +
                ", temperature=" + temperature +
                '}';
    }
}
