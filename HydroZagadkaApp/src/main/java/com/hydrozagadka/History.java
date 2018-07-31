package com.hydrozagadka;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column(name="level_of_water_date")
    private LocalDateTime levelOfWaterDate;

    @Column(name = "temperature_date")
    private LocalDateTime temperatureDate;
    @Column(name="ice_phenomenon")
    private Integer icePhenomenon;
    @Column(name="ice_phenomenon_date")
    private LocalDateTime icePhenomenonDate;
    @Column(name="overgrowth_phenomenon")
    private Integer overgrowthPhenomenon;
    @Column(name="overgrowth_phenomenon_date")
    private LocalDateTime overgrowthPhenomenonDate;

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


    public History(LocalDate date, Double waterDeep, Double flow, Double temperature, LocalDateTime levelOfWaterDate, LocalDateTime temperatureDate, Integer icePhenomenon, LocalDateTime icePhenomenonDate, Integer overgrowthPhenomenon, LocalDateTime overgrowthPhenomenonDate, WaterContainer waterContainers, Long containerId) {
        this.date = date;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
        this.levelOfWaterDate = levelOfWaterDate;
        this.temperatureDate = temperatureDate;
        this.icePhenomenon = icePhenomenon;
        this.icePhenomenonDate = icePhenomenonDate;
        this.overgrowthPhenomenon = overgrowthPhenomenon;
        this.overgrowthPhenomenonDate = overgrowthPhenomenonDate;
        this.waterContainers = waterContainers;
        this.containerId = containerId;
    }

    public LocalDateTime getLevelOfWaterDate() {
        return levelOfWaterDate;
    }

    public void setLevelOfWaterDate(LocalDateTime levelOfWaterDate) {
        this.levelOfWaterDate = levelOfWaterDate;
    }

    public LocalDateTime getTemperatureDate() {
        return temperatureDate;
    }

    public void setTemperatureDate(LocalDateTime temperatureDate) {
        this.temperatureDate = temperatureDate;
    }

    public Integer getIcePhenomenon() {
        return icePhenomenon;
    }

    public void setIcePhenomenon(Integer icePhenomenon) {
        this.icePhenomenon = icePhenomenon;
    }

    public LocalDateTime getIcePhenomenonDate() {
        return icePhenomenonDate;
    }

    public void setIcePhenomenonDate(LocalDateTime icePhenomenonDate) {
        this.icePhenomenonDate = icePhenomenonDate;
    }

    public Integer getOvergrowthPhenomenon() {
        return overgrowthPhenomenon;
    }

    public void setOvergrowthPhenomenon(Integer overgrowthPhenomenon) {
        this.overgrowthPhenomenon = overgrowthPhenomenon;
    }

    public LocalDateTime getOvergrowthPhenomenonDate() {
        return overgrowthPhenomenonDate;
    }

    public void setOvergrowthPhenomenonDate(LocalDateTime overgrowthPhenomenonDate) {
        this.overgrowthPhenomenonDate = overgrowthPhenomenonDate;
    }
}
