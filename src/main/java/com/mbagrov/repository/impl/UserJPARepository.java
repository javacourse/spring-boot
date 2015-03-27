package com.mbagrov.repository.impl;

import com.mbagrov.dto.User;
import com.mbagrov.repository.api.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Odour on 27.03.2015.
 */
@Repository
@Transactional(readOnly = true)
public class UserJPARepository extends AbstractJPARepository<User, Long> implements IUserRepository{
}
