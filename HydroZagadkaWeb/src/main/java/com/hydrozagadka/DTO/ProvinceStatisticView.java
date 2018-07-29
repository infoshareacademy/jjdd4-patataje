package com.hydrozagadka.DTO;

public class ProvinceStatisticView {
    private String province;
    private Long views;

    public ProvinceStatisticView(String province, Long views) {
        this.province = province;
        this.views = views;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
