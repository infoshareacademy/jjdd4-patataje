package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.StationView;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Stateless
public class WaterContainerAndStationMapper {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<WaterContainerView> mapToWaterContainerView (List<WaterContainer> wt){
       List<WaterContainerView> wtv = new ArrayList<>();

        for (WaterContainer w: wt) {
            wtv.add(new WaterContainerView(w.getId(),w.getStationName()));
        }
        return wtv.stream().filter(distinctByKey(WaterContainerView::getName)).collect(Collectors.toList());
    }

    public List<StationView> mapToStationView(List<WaterContainer> wt){
        List<StationView> stationViews = new ArrayList<>();
        for (WaterContainer w: wt) {
            stationViews.add(new StationView(w.getId(),w.getContainerName()));
        }
    return stationViews;
    }

}
