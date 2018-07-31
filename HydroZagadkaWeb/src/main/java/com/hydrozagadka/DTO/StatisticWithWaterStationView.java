package com.hydrozagadka.DTO;

import javax.ejb.Stateless;


public class StatisticWithWaterStationView {

    private String stationName;
    private Long views;

    public StatisticWithWaterStationView(Long id, String stationName, Long views) {

        this.stationName = stationName;
        this.views = views;
    }

    public StatisticWithWaterStationView(String stationName, Long views) {
        this.stationName = stationName;
        this.views = views;
    }

    public String getstationName() {
        return stationName;
    }

    public void setstationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StatisticWithWaterStationView{");
        sb.append("stationName='").append(stationName).append('\'');
        sb.append(", views=").append(views);
        sb.append('}');
        return sb.toString();
    }
}
