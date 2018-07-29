package com.hydrozagadka.dao;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.Statistics;
import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class AdminStatsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsersList(){
        Query q = entityManager.createQuery("select u from User u");

        return q.getResultList();
    }

    public List<Statistics> getStatistics (){
        Query q = entityManager.createQuery("Select Statistics.waterContainer from  Statistics where views>0");
        return q.getResultList();
    }
}
