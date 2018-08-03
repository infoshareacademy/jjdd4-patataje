package com.hydrozagadka.DTO;

import java.util.List;

public class UserFavsView {
    private List<String> favContainers;


    public UserFavsView(List<String> favContainers) {
        this.favContainers = favContainers;
    }

    public List<String> getfavContainers() {
        return favContainers;
    }

    public void setfavContainers(List<String> favContainers) {
        this.favContainers = favContainers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserFavsView{");
        sb.append("favContainers=").append(favContainers);
        sb.append('}');
        return sb.toString();
    }
}
