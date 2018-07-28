package com.hydrozagadka.dao;

import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class WaterContainerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(WaterContainer waterContainer) {
        entityManager.persist(waterContainer);
        return waterContainer.getId();
    }

    public WaterContainer update(WaterContainer waterContainer) {
        return entityManager.merge(waterContainer);
    }

    public void delete(Long id) {
        final WaterContainer waterContainer = entityManager.find(WaterContainer.class, id);
        if (waterContainer != null) {
            entityManager.remove(waterContainer);
        }
    }

    public List<WaterContainer> getWaterContainerByProvince(String province){
        Query q = entityManager.createQuery("select w from WaterContainer w where w.province like :province");
        q.setParameter("province",province);
        return q.getResultList();
    }

    public List<WaterContainer> getWaterContainerByProvinceAndwaterContainer(String province,String waterContainerName){
        List<WaterContainer> waterContainers = getWaterContainerByProvince(province);
        Query q = entityManager.createQuery("select wt from WaterContainer wt where wt.stationName like :watercname");
        q.setParameter("watercname","%"+waterContainerName+"%");
        waterContainers = q.getResultList();
        return q.getResultList();
    }

    public WaterContainer findById(Long id) {
        return entityManager.find(WaterContainer.class, id);
    }

    public List<WaterContainer> findAll() {
        final Query query = entityManager.createQuery("SELECT w FROM WaterContainer w");

        return query.getResultList();
    }
}
