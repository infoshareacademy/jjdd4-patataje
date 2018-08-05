package com.hydrozagadka.dao;

import com.hydrozagadka.DTO.ProvinceStatisticView;
import com.hydrozagadka.DTO.StatisticWithWaterStationView;
import com.hydrozagadka.DTO.UserDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdminStatsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserDetails> getAllUsersList() {
        Query q = entityManager.createQuery("select u.email, u.adminaaa, u.name, u.stats from User u");
        List<Object[]> result = q.getResultList();
        List<UserDetails> userDetails = result.stream()
                .map(o -> new UserDetails((String) o[0], (boolean) o[1], (String) o[2], (Integer) o[3]))
                .collect(Collectors.toList());
        return userDetails;
    }

    public List<StatisticWithWaterStationView> getStatistics() {
        Query q = entityManager.createQuery("SELECT w.containerName, s.views FROM  Statistics s JOIN s.waterContainer w where w.id=s.waterContainer.id order by s.views desc").setMaxResults(10);
        List<Object[]> result = q.getResultList();
        List<StatisticWithWaterStationView> statisticWithWaterStationViews = result.stream()
                .map(o -> new StatisticWithWaterStationView((String) o[0], (Long) o[1]))
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

//    public List<UserFavsView> getUserFavsContainers(){
//
//        Query q = entityManager.createQuery("select u.waterContainerId from User u left join WaterContainer uw on uw.users = u.waterContainerId left join WaterContainer wc on wc.id = uw.container_id  where u.id=:id");
//q.setParameter("id", Long.valueOf(id));
//        List<Object[]> favs = q.getResultList();
//        List<UserFavsView> userFavsView = favs.stream().map(o -> new UserFavsView((List<WaterContainer>) o[0])).collect(Collectors.toList());
//
//        return userFavsView;
//    }

}
