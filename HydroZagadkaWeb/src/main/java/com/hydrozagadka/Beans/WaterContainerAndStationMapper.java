package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.StationView;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class WaterContainerAndStationMapper {

    public List<WaterContainerView> mapToWaterContainerView (List<WaterContainer> wt){
       List<WaterContainerView> wtv = new ArrayList<>();

        for (WaterContainer w: wt) {
            wtv.add(new WaterContainerView(w.getId(),w.getStationName()));
        }
        return wtv;
    }

    public List<StationView> mapToStationView(List<WaterContainer> wt){
        List<StationView> stationViews = new ArrayList<>();
        for (WaterContainer w: wt) {
            stationViews.add(new StationView(w.getId(),w.getContainerName()));
        }
return stationViews;
    }

}
