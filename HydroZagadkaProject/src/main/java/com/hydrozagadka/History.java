package com.hydrozagadka;

import java.time.LocalDate;

public class History {

    private LocalDate date;
    private Double waterDeep;
    private Double flow;
    private Double temperature;

    public History(LocalDate date, Double waterDeep, Double flow, Double temperature) {
        this.date = date;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getWaterDeep() {
        return waterDeep;
    }

    public Double getFlow() {
        return flow;
    }

    public Double getTemperature() {
        return temperature;
    }
}
