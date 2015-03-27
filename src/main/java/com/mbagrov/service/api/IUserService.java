package com.mbagrov.service.api;

import com.mbagrov.dto.User;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IUserService {

    public List<User> findAll();

    public void saveOrUpdate(User user);

    public User getById(Long id);

    public User getByUsername(String username) throws Exception;
}
