package com.hydrozagadka.dao;

import com.hydrozagadka.History;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
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

    public List<History> findWaterdeepBetweenTwoDates(LocalDate startDate, LocalDate endDate, Long id) {
        final Query query = entityManager.createQuery("SELECT max(History.waterDeep), min(History.waterDeep) from History where History.waterContainers.id = :id and History.date > :startDate and History.date < :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
