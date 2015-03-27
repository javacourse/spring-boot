package com.mbagrov.repository.crud;

import com.mbagrov.dto.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Odour on 26.03.2015.
 */

public interface PostCrudRepository extends CrudRepository<Post, Long> {

}
