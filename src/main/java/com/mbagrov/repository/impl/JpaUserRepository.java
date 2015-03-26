package com.mbagrov.repository.impl;

import com.mbagrov.dto.User;
import com.mbagrov.repository.api.UserRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */
@Repository
public class JpaUserRepository implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
