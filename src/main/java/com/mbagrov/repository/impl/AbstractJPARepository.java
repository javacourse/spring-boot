package com.mbagrov.repository.impl;

import com.mbagrov.repository.api.IGenericRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */

@Transactional(readOnly = true)
public abstract class AbstractJPARepository<T, ID extends Serializable> implements IGenericRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractJPARepository() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    private EntityManager getEntityManager() {
        entityManager.setFlushMode(FlushModeType.COMMIT);
        return entityManager;
    }

    private Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(ID id) {
        T entity = (T) getEntityManager().find(getPersistentClass(), id);

        if (entity == null) {
            throw new IllegalArgumentException("No such entity");
        }

        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(ID id, boolean lock) {
        T entity;

        if (lock) {
            entity = (T) getEntityManager().find(getPersistentClass(), id, LockModeType.OPTIMISTIC);
        } else {
            entity = getById(id);
        }

        if (entity == null) {
            throw new IllegalArgumentException("No such entity");
        }

        return entity;
    }

    @Override
    public List<T> executeQuery(String query) {
        List<T> result = new ArrayList<>();

        result = getEntityManager().createQuery(query, getPersistentClass()).getResultList();

        return result;
    }

    @Override
    public List<T> findAll() {
        String query = "from " + getPersistentClass().getSimpleName();
        return executeQuery(query);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveOrUpdate(T entity) {
        if (isExist(entity)) {
            update(entity);
        } else {
            save(entity);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(ID id) {
        getEntityManager().remove(getById(id));
    }

    @Override
    public boolean isExistById(ID id) {
        return getEntityManager().contains(getById(id));
    }

    @Override
    public boolean isExist(T entity) {
        return getEntityManager().contains(entity);
    }
}
