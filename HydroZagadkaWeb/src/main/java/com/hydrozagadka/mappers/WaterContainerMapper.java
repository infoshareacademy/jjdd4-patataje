package com.hydrozagadka.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.Model.StationView;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Stateless
public class WaterContainerMapper {



    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<WaterContainerView> mapToWaterContainerView(List<WaterContainer> wt) throws JsonProcessingException {

        return wt.stream()
                .filter(distinctByKey(WaterContainer::getContainerName))
                .map(w->new WaterContainerView(w.getId(), w.getContainerName()))
                .collect(Collectors.toList());
    }

    public List<StationView> mapToStationView(List<WaterContainer> wt) throws JsonProcessingException {
        return wt.stream()
                .map(w-> new StationView(w.getId(), w.getStationName()))
                .collect(Collectors.toList());
    }

}
