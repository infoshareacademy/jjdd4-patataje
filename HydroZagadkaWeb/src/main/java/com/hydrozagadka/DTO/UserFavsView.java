package com.hydrozagadka.DTO;

public class UserFavsView {
    private String favContainer;

    public UserFavsView(String favContainer) {
        this.favContainer = favContainer;
    }

    public String getFavContainer() {
        return favContainer;
    }

    public void setFavContainer(String favContainer) {
        this.favContainer = favContainer;
    }

    @Override
    public String toString() {
        return "UserFavsView{" +
                "favContainer='" + favContainer + '\'' +
                '}';
    }
}
