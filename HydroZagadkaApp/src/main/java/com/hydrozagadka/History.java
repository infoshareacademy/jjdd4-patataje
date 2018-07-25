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

    @Column(name="stan_wody_data_pomiaru")
    private LocalDateTime stanWodyDataPomiaru;

    @Column(name = "temperatura_wody_data_pomiaru")
    private LocalDateTime temperaturaWodyDataPomiaru;
    @Column(name="zjawisko_lodowe")
    private Integer zjawiskoLodowe;
    @Column(name="zjawisko_lodowe_data_pomiaru")
    private LocalDateTime zjawiskoLodoweDataPomiaru;
    @Column(name="zjawisko_zarastania")
    private Integer zjawiskoZarastania;
    @Column(name="zjawisko_zarastania_data_pomiaru")
    private LocalDateTime zjawiskoZarastaniaDataPomiaru;

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


    public History(LocalDate date, Double waterDeep, Double flow, Double temperature, LocalDateTime stanWodyDataPomiaru, LocalDateTime temperaturaWodyDataPomiaru, Integer zjawiskoLodowe, LocalDateTime zjawiskoLodoweDataPomiaru, Integer zjawiskoZarastania, LocalDateTime zjawiskoZarastaniaDataPomiaru, WaterContainer waterContainers, Long containerId) {
        this.date = date;
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
        this.stanWodyDataPomiaru = stanWodyDataPomiaru;
        this.temperaturaWodyDataPomiaru = temperaturaWodyDataPomiaru;
        this.zjawiskoLodowe = zjawiskoLodowe;
        this.zjawiskoLodoweDataPomiaru = zjawiskoLodoweDataPomiaru;
        this.zjawiskoZarastania = zjawiskoZarastania;
        this.zjawiskoZarastaniaDataPomiaru = zjawiskoZarastaniaDataPomiaru;
        this.waterContainers = waterContainers;
        this.containerId = containerId;
    }

    public LocalDateTime getStanWodyDataPomiaru() {
        return stanWodyDataPomiaru;
    }

    public void setStanWodyDataPomiaru(LocalDateTime stanWodyDataPomiaru) {
        this.stanWodyDataPomiaru = stanWodyDataPomiaru;
    }




    public LocalDateTime getTemperaturaWodyDataPomiaru() {
        return temperaturaWodyDataPomiaru;
    }

    public void setTemperaturaWodyDataPomiaru(LocalDateTime temperaturaWodyDataPomiaru) {
        this.temperaturaWodyDataPomiaru = temperaturaWodyDataPomiaru;
    }

    public Integer getZjawiskoLodowe() {
        return zjawiskoLodowe;
    }

    public void setZjawiskoLodowe(Integer zjawiskoLodowe) {
        this.zjawiskoLodowe = zjawiskoLodowe;
    }

    public LocalDateTime getZjawiskoLodoweDataPomiaru() {
        return zjawiskoLodoweDataPomiaru;
    }

    public void setZjawiskoLodoweDataPomiaru(LocalDateTime zjawiskoLodoweDataPomiaru) {
        this.zjawiskoLodoweDataPomiaru = zjawiskoLodoweDataPomiaru;
    }

    public Integer getZjawiskoZarastania() {
        return zjawiskoZarastania;
    }

    public void setZjawiskoZarastania(Integer zjawiskoZarastania) {
        this.zjawiskoZarastania = zjawiskoZarastania;
    }

    public LocalDateTime getZjawiskoZarastaniaDataPomiaru() {
        return zjawiskoZarastaniaDataPomiaru;
    }

    public void setZjawiskoZarastaniaDataPomiaru(LocalDateTime zjawiskoZarastaniaDataPomiaru) {
        this.zjawiskoZarastaniaDataPomiaru = zjawiskoZarastaniaDataPomiaru;
    }
}
