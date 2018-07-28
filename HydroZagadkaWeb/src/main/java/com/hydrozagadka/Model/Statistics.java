package com.hydrozagadka.Model;

import com.hydrozagadka.WaterContainer;

import javax.persistence.*;

@Entity
@Table(name = "STATISTICS")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="container_id",unique = true)
    private WaterContainer waterContainer;

    @Column(name = "views")
    private Long views;


    public Statistics(){}

    public Statistics(Long views, WaterContainer waterContainer) {
        this.views = views;
        this.waterContainer = waterContainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }

    public void setWaterContainer(WaterContainer waterContainer) {
        this.waterContainer = waterContainer;
    }
}
