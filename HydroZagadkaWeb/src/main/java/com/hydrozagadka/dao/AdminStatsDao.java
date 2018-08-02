package com.hydrozagadka.dao;

import com.hydrozagadka.DTO.ProvinceStatisticView;
import com.hydrozagadka.DTO.StatisticWithWaterStationView;
import com.hydrozagadka.History;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdminStatsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsersList(){
        Query q = entityManager.createQuery("select u from User u");

        return q.getResultList();
    }

    public List<StatisticWithWaterStationView> getStatistics (){
        Query q = entityManager.createQuery("SELECT w.containerName, s.views FROM  Statistics s JOIN s.waterContainer w where w.id=s.waterContainer.id order by s.views desc").setMaxResults(10);
        List<Object[]> result = q.getResultList();
        List<StatisticWithWaterStationView> statisticWithWaterStationViews = result.stream()
                .map(o-> new StatisticWithWaterStationView((String) o[0],(Long) o[1]))
                .collect(Collectors.toList());
        return statisticWithWaterStationViews;
    }

    public List<ProvinceStatisticView> getStatsByProvince() {
        Query q = entityManager.createQuery("select w.province,SUM(s.views) as views  from Statistics s JOIN s.waterContainer w where w.id=s.waterContainer.id and s.views>0 group by w.province order by views desc");
        List<Object[]> objects = q.getResultList();
       List<ProvinceStatisticView> provinceStatisticViews = objects.stream()
               .map(o -> new ProvinceStatisticView((String) o[0], (Long) o[1]))
               .collect(Collectors.toList());
        return provinceStatisticViews;
    }

}
