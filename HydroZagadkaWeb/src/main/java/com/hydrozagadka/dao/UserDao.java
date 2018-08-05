package com.hydrozagadka.dao;


import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByToken(String id) {
        Query q = entityManager.createQuery("select u from User u where u.token = :token");
        q.setParameter("token", id);
        User user = null;
        try {
            user = (User) q.getSingleResult();
            user.setStats(user.getStats() + 1);
            entityManager.merge(user);
            return user;
        } catch (NoResultException e) {
            return user;
        }
    }

    public void save(User user) {
        if (user != null) {
            entityManager.persist(user);
        }
    }

    public User update(User user) {
        return entityManager.merge(user);
    }
    public User findById(Long id){
        return entityManager.find(User.class,id);
    }
    public List<WaterContainer> getFavourites(Long id){
        return entityManager.find(User.class,id).getWaterContainerId();
    }
}