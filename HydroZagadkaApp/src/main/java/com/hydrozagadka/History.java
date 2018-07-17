package com.hydrozagadka;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORIES")
public class History {

    @Column(name = "dates")
    private LocalDate date;
    @Column(name = "waterdeeps")
    private Double waterDeep;
    @Column(name = "flows")
    private Double flow;
    @Column(name = "temperatures")
    private Double temperature;
    @ManyToOne
    @JoinColumn(name = "container_id")
    private WaterContainer waterContainers;

    public LocalDate getDate() {
        return date;
    }

    public History setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Double getWaterDeep() {
        return waterDeep;
    }

    public History setWaterDeep(Double waterDeep) {
        this.waterDeep = waterDeep;
        return this;
    }

    public Double getFlow() {
        return flow;
    }

    public History setFlow(Double flow) {
        this.flow = flow;
        return this;
    }

    public Double getTemperature() {
        return temperature;
    }

    public History setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public WaterContainer getWaterContainers() {
        return waterContainers;
    }

    public History setWaterContainers(WaterContainer waterContainers) {
        this.waterContainers = waterContainers;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("History{");
        sb.append("date=").append(date);
        sb.append(", waterDeep=").append(waterDeep);
        sb.append(", flow=").append(flow);
        sb.append(", temperature=").append(temperature);
        sb.append(", waterContainers=").append(waterContainers);
        sb.append('}');
        return sb.toString();
    }
}
