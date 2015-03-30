package com.myapp.dao.impl;

import com.myapp.dao.api.IPersonDAO;
import com.myapp.model.Person;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("personDao")
@Transactional(readOnly = true)
public class PersonDaoImpl extends AbstractHibernateDAO<Person, Long> implements IPersonDAO{

	@Override
	public boolean isExist(Person entity)
	{
		
//		return !findByCriteria(Restrictions.eq("name", entity.getName())).isEmpty();
		return false;
	}


    @Override
    public List<Person> searchByParams(String firstName, String city) {

        String hqlQuery = "select distinct p from Person p join p.addresses a where p.firstName like :firstName and a.city like :city";

        Query query = getSession().createQuery(hqlQuery);
        query.setParameter("firstName", firstName+"%");
        query.setParameter("city", city+"%");
        List<Person> results = query.list();
        return results;
    }

}
