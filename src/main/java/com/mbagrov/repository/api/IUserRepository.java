package com.mbagrov.repository.api;

import com.mbagrov.dto.User;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IUserRepository extends IGenericRepository<User, Long> {

    public User getByUsername(String username) throws Exception ;

}
