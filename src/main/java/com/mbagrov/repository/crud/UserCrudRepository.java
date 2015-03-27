package com.mbagrov.repository.crud;

import com.mbagrov.dto.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Odour on 26.03.2015.
 */
public interface UserCrudRepository extends CrudRepository<User, Long> {

}
