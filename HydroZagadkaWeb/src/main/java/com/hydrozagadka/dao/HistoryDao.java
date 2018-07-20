package com.hydrozagadka.dao;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.MinAndMaxValues;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public MinAndMaxValues findWaterdeepBetweenTwoDates(LocalDate startDate, LocalDate endDate, Long id) {
        final Query query = entityManager.createQuery("SELECT max(h.waterDeep), min(h.waterDeep) from History h where h.waterContainers.id = :id and h.date > :startDate and h.date < :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("id", id);

        List<Object[]> result = query.getResultList();
        Object[] o = result.get(0);
        return new MinAndMaxValues((Double) o[0], (Double) o[1]);
    }
}
