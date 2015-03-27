package com.mbagrov.service.impl;

import com.mbagrov.dto.User;
import com.mbagrov.repository.api.IUserRepository;
import com.mbagrov.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.saveOrUpdate(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUsername(String username) throws Exception {
        return userRepository.getByUsername(username);
    }
}
