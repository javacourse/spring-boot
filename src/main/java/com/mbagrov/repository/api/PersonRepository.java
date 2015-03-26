package com.mbagrov.repository.api;

import com.mbagrov.dto.Person;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */
public interface PersonRepository {

    List<Person> findAll();

}
