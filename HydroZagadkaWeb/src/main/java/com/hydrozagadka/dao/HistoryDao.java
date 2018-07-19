package com.hydrozagadka.dao;

import com.hydrozagadka.History;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class HistoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(History history) {
        entityManager.persist(history);
        return history.getId();
    }

    public History update(History history) {
        return entityManager.merge(history);
    }

    public void delete(Long id) {
        final History history = entityManager.find(History.class, id);
        if (history != null) {
            entityManager.remove(history);
        }
    }

    public History findById(Long id) {
        return entityManager.find(History.class, id);
    }

    public List<History> findAll() {
        final Query query = entityManager.createQuery("SELECT h FROM History h");

        return query.getResultList();
    }
    public List<History> getHistoryFormWaterContainer(Long id){
        Query query = entityManager.createQuery("SELECT h FROM History h join h.WaterContainer wt WHERE wt.id=:Id");
        query.setParameter("Id",id);
        return query.getResultList();
    }
}
