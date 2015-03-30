package com.myapp.service.impl;

import com.myapp.dao.api.IPersonDAO;
import com.myapp.model.Person;
import com.myapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private IPersonDAO personDao;

    public List<Person> findAll(){
        return   personDao.findAll();
    }
    public Person getById(Long id){
        return personDao.getById(id);
    }
    public void saveOrUpdate(Person person){
         personDao.saveOrUpdate(person);
    }

    @Override
    public void deletePerson(Long id) {
        personDao.deleteById(id);
    }

    @Override
    public List<Person> searchByParams(Map<String, String> searchParams) {
        String firstName = (String) searchParams.get("firstName");
        String city = (String) searchParams.get("city");

        return personDao.searchByParams(firstName, city);
    }

}
