package com.mbagrov.repository.impl;

import com.mbagrov.dto.Post;
import com.mbagrov.repository.api.PostRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */

@Repository
public class JpaPostRepository implements PostRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("select p from Post p", Post.class)
                .getResultList();
    }
}
