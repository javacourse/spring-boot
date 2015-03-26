package com.mbagrov.service.impl;

import com.mbagrov.dto.User;
import com.mbagrov.repository.api.UserRepository;
import com.mbagrov.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
