package com.hydrozagadka.dao;


import com.hydrozagadka.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Stateless
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User findById(String id){
        Query q = entityManager.createQuery("select u from User u where u.id = :token");
        q.setParameter("token", Long.valueOf(id));
        User user = null;
        try {
            user = (User) q.getSingleResult();
            user.setStats(user.getStats()+1);
            entityManager.merge(user);
            return user;
        }catch (NoResultException e){
            return user;
        }
    }

    public void save(User user){
        if(user!=null) {
            entityManager.persist(user);
        }
    }
    public User update(User user){
        return entityManager.merge(user);
    }

}