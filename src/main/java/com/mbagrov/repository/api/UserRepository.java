package com.mbagrov.repository.api;

import com.mbagrov.dto.User;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */
public interface UserRepository {

    List<User> findAll();

}
