package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class Mapper {

    public List<WaterContainerView> map (List<WaterContainer> wt){
       List<WaterContainerView> wtv = new ArrayList<>();

        for (WaterContainer w: wt) {
            wtv.add(new WaterContainerView(w.getId(),w.getStationName()));

        }
        return wtv;
    }
}
