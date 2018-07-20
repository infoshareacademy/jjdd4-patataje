package com.hydrozagadka.dao;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.ChartHistory;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public List<History> findAll() {
        final Query query = entityManager.createQuery("SELECT h FROM History h");

        return query.getResultList();
    }

    public List<ChartHistory> getHistoryByWaterContainer(Long id) {
        Query q = entityManager.createQuery(
                "SELECT h FROM History h WHERE h.waterContainers.id = :id");
        q.setParameter("id", id);
        return ((List<History>) q.getResultList()).stream()
                .map(history -> new ChartHistory(
                        history.getWaterDeep(),
                        history.getFlow(),
                        history.getTemperature()))
                .collect(toList());
    }
}
