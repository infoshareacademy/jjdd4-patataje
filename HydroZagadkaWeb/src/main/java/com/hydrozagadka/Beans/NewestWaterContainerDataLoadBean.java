package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Stateless
public class NewestWaterContainerDataLoadBean {

    @Inject
    WaterContainerDao waterContainerDao;

    public boolean loadNewestWaterContainerToDatabase(List<NewestWaterContainerData> apiData){


        apiData = apiData.stream()
                .filter(s->s.getId()!=waterContainerDao.findById(s.getId()).getId()).collect(Collectors.toList());

        if(apiData.size()!=0){
            apiData.stream().map(s-> waterContainerDao
                    .save(new WaterContainer(s.getId(),
                            s.getRzeka(),
                            s.getStacja(),
                            s.getWojewodztwo(),
                            new ArrayList<>())));
            return true;
        }
        return false;
    }
}
