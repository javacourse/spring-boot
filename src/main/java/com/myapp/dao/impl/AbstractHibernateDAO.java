package com.myapp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.myapp.dao.api.IGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class AbstractHibernateDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;


	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	/**Obtains the current session. Default flush mode is COMMIT.
	 * 
	 * @return the current session
	 */   	
	public Session getSession() {
   		Session session = getSessionFactory().getCurrentSession();
   		session.setFlushMode(FlushMode.COMMIT);
       return session;
    }

	public Class<T> getPersistentClass() {
        return persistentClass;
    }

	@Override
	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		T entity = (T) getSession().get(getPersistentClass(), id);
		if (entity == null)
		{
			throw new IllegalArgumentException("No such entity!");
		}
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(ID id, boolean lock) {
		if (lock) {
			return (T) getSession().get(getPersistentClass(), id,
					LockOptions.UPGRADE);
		} else
			return getById(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T loadById(ID id) {
		return (T) getSession().load(getPersistentClass(), id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(ID id) 	{
		getSession().delete(loadById(id));
	}

	@Override
    @Transactional
	public List<T> findAll() {
        return findByCriteria();
    }
	
	/**
     * Use this inside subclasses as a convenience method.
     */
	@Override
    @SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());        
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
   }
    
   
	
	/**
	 * This method will execute an HQL query and return the number of affected entities.
	 */
	protected int executeQuery(String query, String namedParams[],	Object params[]) {
		Query q = getSession().createQuery(query);
		
		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}

		return q.executeUpdate();
	}
	
	protected int executeQuery(String query) {
		return executeQuery(query, null, null);
	}
	
	/**
	 * This method will execute a Named HQL query and return the number of affected entities.
	 */
	protected int executeNamedQuery(String namedQuery, String namedParams[],	Object params[]) {
		Query q = getSession().getNamedQuery(namedQuery);
		
		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}

		return q.executeUpdate();
	}
	
	protected int executeNamedQuery(String namedQuery) {
		return executeNamedQuery(namedQuery, null, null);
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(exampleInstance).excludeZeroes().enableLike().ignoreCase();
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }	
	
	@Override
	public void merge(T entity)
	{
		getSession().merge(entity);
		
	}

	

	@Override
	public abstract boolean isExist(T entity);
	
}
