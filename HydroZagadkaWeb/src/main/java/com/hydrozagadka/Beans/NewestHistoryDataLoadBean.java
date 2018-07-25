package com.hydrozagadka.Beans;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Stateless
public class NewestHistoryDataLoadBean {

    @Inject
    HistoryDao historyDao;

    @Inject
    WaterContainerDao waterContainerDao;

    @Inject
    NewestWaterContainerData newestWaterContainerData;


    public boolean loadNewestHistoryToDatabase(List<NewestWaterContainerData> apiData) {


        apiData.forEach(s -> {
            LocalDate checkdate;
            if(s.getStanWodyDataPomiaru()==null){
                checkdate = LocalDate.now();
            }else{
                checkdate = s.getStanWodyDataPomiaru().toLocalDate();
            }
            historyDao.save(new History(
                checkdate,
                s.getStanWody(),
                0.0,
                s.getTemperatura(),
                s.getStanWodyDataPomiaru(),
                s.getTemperaturaWodyDataPomiaru(),
                s.getZjawiskoLodowe(),
                s.getZjawiskoLodoweDataPomiaru(),
                s.getZjawiskoZarastania(),
                s.getZjawiskoZarastaniaDataPomiaru(),
                waterContainerDao.findById(s.getId()),
                s.getId()));
        });
        return true;
   }
}
