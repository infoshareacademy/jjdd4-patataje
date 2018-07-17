package com.hydrozagadka;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WATER_CONTAINERS")
public class WaterContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "containerName")
    private String containerName;
    @Column
    private String stationName;
    @Column
    private String province;
    @Column
    @OneToMany(mappedBy = "waterContainers", fetch = FetchType.EAGER)
    private List<History> history;

    public Integer getId() {
        return id;
    }

    public WaterContainer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getContainerName() {
        return containerName;
    }

    public WaterContainer setContainerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    public String getStationName() {
        return stationName;
    }

    public WaterContainer setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public WaterContainer setProvince(String province) {
        this.province = province;
        return this;
    }

    public List<History> getHistory() {
        return history;
    }

    public WaterContainer setHistory(List<History> history) {
        this.history = history;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WaterContainer{");
        sb.append("id=").append(id);
        sb.append(", containerName='").append(containerName).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", history=").append(history);
        sb.append('}');
        return sb.toString();
    }
}
