package com.myapp.dao.api;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface IGenericDAO<T, ID extends Serializable> {

	T getById(ID id, boolean lock);

	T getById(ID id);
	
	T loadById(ID id);

	List<T> findAll();
	
	void merge(T entity);	
	
	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	void save(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);
	
	void deleteById(ID id);

	List<T> findByCriteria(Criterion... criterion);	

	boolean isExist(T entity);
	
	
	
}