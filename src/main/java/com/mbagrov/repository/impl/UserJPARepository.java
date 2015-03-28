package com.mbagrov.repository.impl;

import com.mbagrov.dto.User;
import com.mbagrov.repository.api.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
@Repository
@Transactional(readOnly = true)
public class UserJPARepository extends AbstractJPARepository<User, Long> implements IUserRepository{

    @Override
    public User getByUsername(String username) throws Exception {
        String query = "select u from User u where u.username like '" + username + "'";

        List<User> users = executeQuery(query);

        if (users.size() != 1) throw new Exception();

        return users.iterator().next();
    }

}
