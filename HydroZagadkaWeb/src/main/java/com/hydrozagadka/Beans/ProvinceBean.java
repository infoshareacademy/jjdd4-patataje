package com.hydrozagadka.Beans;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class ProvinceBean {
    private List<String> province = Arrays.asList(
            "lubuskie",
            "pomorskie",
            "podkarpackie",
            "warmińsko-mazurskie",
            "zachodniopomorskie",
            "podlaskie",
            "łódzkie",
            "opolskie",
            "śląskie",
            "mazowieckie",
            "dolnośląskie",
            "lubelskie",
            "wielkopolskie",
            "małopolskie",
            "świętokrzyskie",
            "kujawsko-pomorskie");


    public List<String> getProvince() {
        return province;
    }
}
