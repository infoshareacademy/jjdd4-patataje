package com.hydrozagadka;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(date, history.date) &&
                Objects.equals(waterDeep, history.waterDeep) &&
                Objects.equals(flow, history.flow) &&
                Objects.equals(temperature, history.temperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, waterDeep, flow, temperature);
    }
}
