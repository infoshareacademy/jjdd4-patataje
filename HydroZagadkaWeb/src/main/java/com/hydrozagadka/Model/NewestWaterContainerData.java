package com.hydrozagadka.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class NewestWaterContainerData {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @JsonProperty("id_stacji")
    private Long id;
    @JsonProperty("stacja")
    private String station;
    @JsonProperty("rzeka")
    private String container;
    @JsonProperty("województwo")
    private String province;
    @JsonProperty("stan_wody")
    private Double waterLevel;
    @JsonProperty("stan_wody_data_pomiaru")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "pl_PL")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private String waterLevelDate;
    @JsonProperty("temperatura_wody")
    private Double waterTemperature;
    @JsonProperty("temperatura_wody_data_pomiaru")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "pl_PL")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private String  waterTemperatureDate;
    @JsonProperty("zjawisko_lodowe")
    private Integer icePhenomenon;
    @JsonProperty("zjawisko_lodowe_data_pomiaru")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "pl_PL")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private String  icePhenomenonDate;
    @JsonProperty("zjawisko_zarastania")
    private Integer overgrowthPhenomenon;
    @JsonProperty("zjawisko_zarastania_data_pomiaru")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "pl_PL")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private String  overgrowthPhenomenonDate;

    public NewestWaterContainerData() { }

    public NewestWaterContainerData(Long id, String station, String container, String province, Double waterLevel, String  waterLevelDate, Double waterTemperature, String  waterTemperatureDate, Integer icePhenomenon, String  icePhenomenonDate, Integer overgrowthPhenomenon, String  overgrowthPhenomenonDate) {
        this.id = id;
        this.station = station;
        this.container = container;
        this.province = province;
        this.waterLevel = waterLevel;
        this.waterLevelDate = waterLevelDate;
        this.waterTemperature = waterTemperature;
        this.waterTemperatureDate = waterTemperatureDate;
        this.icePhenomenon = icePhenomenon;
        this.icePhenomenonDate = icePhenomenonDate;
        this.overgrowthPhenomenon = overgrowthPhenomenon;
        this.overgrowthPhenomenonDate = overgrowthPhenomenonDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public LocalDate getWaterLevelDate() {
        return Optional.ofNullable(waterLevelDate)
                .map(d -> LocalDateTime.parse(waterLevelDate, dateTimeFormatter))
                .orElse(LocalDateTime.now()).toLocalDate();
    }

    public void setWaterLevelDate(String  waterLevelDate) {
        this.waterLevelDate = waterLevelDate;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public LocalDateTime getWaterTemperatureDate() {
        return Optional.ofNullable(waterTemperatureDate)
                .map(d -> LocalDateTime.parse(waterTemperatureDate, dateTimeFormatter))
                .orElse(LocalDateTime.now());
    }

    public void setWaterTemperatureDate(String  waterTemperatureDate) {
        this.waterTemperatureDate = waterTemperatureDate;
    }

    public Integer getIcePhenomenon() {
        return icePhenomenon;
    }

    public void setIcePhenomenon(Integer icePhenomenon) {
        this.icePhenomenon = icePhenomenon;
    }

    public LocalDateTime getIcePhenomenonDate() {
        return Optional.ofNullable(icePhenomenonDate)
                .map(d -> LocalDateTime.parse(icePhenomenonDate, dateTimeFormatter))
                .orElse(LocalDateTime.now());
    }

    public void setIcePhenomenonDate(String  icePhenomenonDate) {
        this.icePhenomenonDate = icePhenomenonDate;
    }

    public Integer getOvergrowthPhenomenon() {
        return overgrowthPhenomenon;
    }

    public void setOvergrowthPhenomenon(Integer overgrowthPhenomenon) {
        this.overgrowthPhenomenon = overgrowthPhenomenon;
    }

    public LocalDateTime getOvergrowthPhenomenonDate() {
        return Optional.ofNullable(overgrowthPhenomenonDate)
                .map(d -> LocalDateTime.parse(overgrowthPhenomenonDate, dateTimeFormatter))
                .orElse(LocalDateTime.now());
    }

    public void setOvergrowthPhenomenonDate(String  overgrowthPhenomenonDate) {
        this.overgrowthPhenomenonDate = overgrowthPhenomenonDate;
    }
}
