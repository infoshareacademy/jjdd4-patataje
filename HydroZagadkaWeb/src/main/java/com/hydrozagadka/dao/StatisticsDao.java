package com.hydrozagadka.dao;

import com.hydrozagadka.Model.Statistics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StatisticsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Statistics statistics){
        entityManager.persist(statistics);

    }
    public boolean update(Long containerId){
        Query q = entityManager.createQuery("SELECT s FROM Statistics s where s.waterContainer.id=:id");
        q.setParameter("id",containerId);
        List<Statistics> s =  q.getResultList();
        if(s.size()==0) {
            return false;
        }
        Statistics result = s.get(0);
        result.setViews(result.getViews()+1);
        entityManager.merge(result);
        return true;
    }
}
