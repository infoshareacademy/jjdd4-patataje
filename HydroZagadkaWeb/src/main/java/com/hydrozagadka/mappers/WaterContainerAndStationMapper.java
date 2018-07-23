package com.hydrozagadka.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.Beans.JsonParserBean;
import com.hydrozagadka.Model.StationView;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Stateless
public class WaterContainerAndStationMapper {

    @Inject
    JsonParserBean jsonParserBean;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }








    public String mapToWaterContainerView (List<WaterContainer> wt) throws JsonProcessingException {
       List<WaterContainerView> wtv = new ArrayList<>();

        for (WaterContainer w: wt) {
            wtv.add(new WaterContainerView(w.getId(),w.getStationName()));
        }
        wtv= wtv.stream()
                .filter(distinctByKey(WaterContainerView::getName))
                .collect(Collectors.toList());
        return jsonParserBean.parseToJson(wtv);
    }

    public String mapToStationView(List<WaterContainer> wt) throws JsonProcessingException {
        List<StationView> stationViews = new ArrayList<>();
        for (WaterContainer w: wt) {
            stationViews.add(new StationView(w.getId(),w.getContainerName()));
        }
    return jsonParserBean.parseToJson(stationViews);
    }

}
