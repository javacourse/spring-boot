package com.mbagrov.repository.api;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IGenericRepository <T, ID extends Serializable> {

    T getById(ID id);

    T getById(ID id, boolean lock);

    List<T> executeQuery(String query);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void saveOrUpdate(T entity);

    void delete(T entity);

    void deleteById(ID id);

    boolean isExistById(ID id);

    boolean isExist(T entity);

}
