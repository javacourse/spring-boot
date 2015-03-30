package com.myapp.dao.api;

import com.myapp.model.Person;

import java.util.List;

public interface IPersonDAO extends IGenericDAO<Person, Long>
{


    List<Person> searchByParams(String firstName,String city);
}
