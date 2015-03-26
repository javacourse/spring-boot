package com.mbagrov.repository.impl;

import com.mbagrov.dto.Person;
import com.mbagrov.repository.api.PersonRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */

@Repository
public class JpaPersonRepository implements PersonRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        return this.entityManager.createQuery("select p from Person p", Person.class)
                .getResultList();
    }
}
