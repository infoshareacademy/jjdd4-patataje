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
public class WaterContainerMapper {

    @Inject
    JsonParserBean jsonParserBean;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public String mapToWaterContainerView(List<WaterContainer> wt) throws JsonProcessingException {

        return jsonParserBean.parseToJson(wt.stream().filter(distinctByKey(WaterContainer::getContainerName))
                .map(w->new WaterContainerView(w.getId(), w.getStationName()))
                .collect(Collectors.toList()));
    }

    public String mapToStationView(List<WaterContainer> wt) throws JsonProcessingException {
        return jsonParserBean.parseToJson(wt.stream()
                .map(w-> new StationView(w.getId(), w.getContainerName()))
                .collect(Collectors.toList()));
    }

}
