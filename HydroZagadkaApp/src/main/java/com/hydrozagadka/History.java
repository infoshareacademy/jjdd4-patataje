package com.hydrozagadka;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORIES")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dates")
    private LocalDate date;
    @Column(name = "water_deeps")
    private Double waterDeep;
    @Column(name = "flows")
    private Double flow;
    @Column(name = "temperatures")
    private Double temperature;
    @ManyToOne
    @JoinColumn(name = "container_id")
    private WaterContainer waterContainers;

    @Transient
    private Long containerId;

    public History() {
    }

    public History(LocalDate date, Double waterDeep, Double flow, Double temperature) {
        this.date = date;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public WaterContainer getWaterContainers() {
        return waterContainers;
    }

    public void setWaterContainers(WaterContainer waterContainers) {
        this.waterContainers = waterContainers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("History{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", waterDeep=").append(waterDeep);
        sb.append(", flow=").append(flow);
        sb.append(", temperature=").append(temperature);
        sb.append(", waterContainers=").append(waterContainers);
        sb.append('}');
        return sb.toString();
    }
}
