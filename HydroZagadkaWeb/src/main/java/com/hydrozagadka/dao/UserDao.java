package com.hydrozagadka.dao;


import com.hydrozagadka.Model.User;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public User findById(Long id){
       return entityManager.find(User.class,id);
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
