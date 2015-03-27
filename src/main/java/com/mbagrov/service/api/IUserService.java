package com.mbagrov.service.api;

import com.mbagrov.dto.User;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IUserService {

    public List<User> findAll();

}
