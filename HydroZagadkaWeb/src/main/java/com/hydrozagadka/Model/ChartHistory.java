package com.hydrozagadka.Model;

import java.time.LocalDate;

public class ChartHistory {


    private Double waterDeep;

    private Double flow;

    private Double temperature;

    private LocalDate date;


    public ChartHistory(Double waterDeep, Double flow, Double temperature,LocalDate date) {
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
        this.date = date;
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


    public void setWaterDeep(Double waterDeep) {
        this.waterDeep = waterDeep;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChartHistory{");
        sb.append("waterDeep=").append(waterDeep);
        sb.append(", flow=").append(flow);
        sb.append(", temperature=").append(temperature);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
