package com.hydrozagadka.Model;

import com.hydrozagadka.WaterContainer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STATISTICS")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(waterContainer, that.waterContainer) &&
                Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, waterContainer, views);
    }

    @OneToOne
    @JoinColumn(name = "container_id", unique = true)
    private WaterContainer waterContainer;

    @Column(name = "views")
    private Long views;

    public Statistics() {
    }

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Statistics{");
        sb.append("id=").append(id);
        sb.append(", waterContainer=").append(waterContainer);
        sb.append(", views=").append(views);
        sb.append('}');
        return sb.toString();
    }
}
