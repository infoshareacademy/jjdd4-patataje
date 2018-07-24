package com.hydrozagadka.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;


public class NewestWaterContainerData {
    @JsonProperty("id_stacji")
    private Long id;
    @JsonProperty("stacja")
    private String stacja;
    private String rzeka;
    @JsonProperty("województwo")
    private String wojewodztwo;
    @JsonProperty("stan_wody")
    private Double stanWody;
    @JsonProperty("stan_wody_data_pomiaru")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime stanWodyDataPomiaru;
    @JsonProperty("temperatura_wody")
    private Double temperatura;
    @JsonProperty("temperatura_wody_data_pomiaru")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime temperaturaWodyDataPomiaru;
    @JsonProperty("zjawisko_lodowe")
    private Integer zjawiskoLodowe;
    @JsonProperty("zjawisko_lodowe_data_pomiaru")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime zjawiskoLodoweDataPomiaru;
    @JsonProperty("zjawisko_zarastania")
    private Integer zjawiskoZarastania;
    @JsonProperty("zjawisko_zarastania_data_pomiaru")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime zjawiskoZarastaniaDataPomiaru;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStacja() {
        return stacja;
    }

    public void setStacja(String stacja) {
        this.stacja = stacja;
    }

    public String getRzeka() {
        return rzeka;
    }

    public void setRzeka(String rzeka) {
        this.rzeka = rzeka;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public Double getStanWody() {
        return stanWody;
    }

    public void setStanWody(Double stanWody) {
        this.stanWody = stanWody;
    }

    public LocalDateTime getStanWodyDataPomiaru() {
        return stanWodyDataPomiaru;
    }

    public void setStanWodyDataPomiaru(LocalDateTime stanWodyDataPomiaru) {
        this.stanWodyDataPomiaru = stanWodyDataPomiaru;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
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
