package com.hydrozagadka.mappers;

import com.hydrozagadka.History;
import com.hydrozagadka.DTO.ChartHistory;
import com.hydrozagadka.dao.HistoryDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class HistoryMapper {

    @Inject
    private HistoryDao historyDao;

    public List<ChartHistory> mapToChartHistory(List<History> listOfHistoryToMap) {

        return listOfHistoryToMap.stream()
                .map(history -> new ChartHistory(
                        history.getWaterDeep(),
                        history.getFlow(),
                        history.getTemperature(),
                        history.getDate()))
                .collect(toList());
    }
}
