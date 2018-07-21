package com.hydrozagadka.dao;

import com.hydrozagadka.History;
import com.hydrozagadka.Model.ChartHistory;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.Model.MinAndMaxValues;

import javax.ejb.Local;
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

    public List<ChartHistory> getHistoryByWaterContainer(Long id, LocalDate startDate, LocalDate endDate) {
        Query q = entityManager.createQuery(
                "SELECT h FROM History h WHERE h.waterContainers.id = :id AND h.date BETWEEN :startDate AND :endDate");
        q.setParameter("id", id);
        q.setParameter("startDate",startDate);
        q.setParameter("endDate",endDate);
        return ((List<History>) q.getResultList()).stream()
                .map(history -> new ChartHistory(
                        history.getWaterDeep(),
                        history.getFlow(),
                        history.getTemperature()))
                .collect(toList());
    }
}
