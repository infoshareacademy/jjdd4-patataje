package com.hydrozagadka.Model;

public class StationView {
    private int id;
    private String name;

    public StationView(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StationView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
