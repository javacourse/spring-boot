package com.myapp.service;

import com.myapp.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    List<Person> findAll();
    Person getById(Long id);
    void saveOrUpdate(Person person);
    void deletePerson(Long id);
    List<Person> searchByParams(Map<String, String> searchParams);
}
