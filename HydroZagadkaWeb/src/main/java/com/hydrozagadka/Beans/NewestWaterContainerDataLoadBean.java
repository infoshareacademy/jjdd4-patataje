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

    public void loadNewestWaterContainerToDatabase(List<NewestWaterContainerData> apiData){


      apiData.forEach(s-> {
          if(waterContainerDao.findById(s.getId())==null){
              waterContainerDao.save(new WaterContainer(
                      s.getId(),
                      s.getRzeka(),
                      s.getStacja(),
                      s.getWojewodztwo(),
                      new ArrayList<>()));
          }
      });
    }
}
