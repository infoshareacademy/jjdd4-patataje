package com.hydrozagadka.DTO;

import com.hydrozagadka.WaterContainer;

import java.util.List;

public class UserFavsView {
    private List<WaterContainer> favContainers;

    public UserFavsView(List<WaterContainer> favContainers) {
        this.favContainers = favContainers;
    }

    public List<WaterContainer> getFavContainers() {
        return favContainers;
    }

    public void setFavContainers(List<WaterContainer> favContainers) {
        this.favContainers = favContainers;
    }

    @Override
    public String toString() {
        return "UserFavsView{" +
                "favContainers=" + favContainers +
                '}';
    }
}
