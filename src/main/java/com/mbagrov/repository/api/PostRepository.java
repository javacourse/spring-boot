package com.mbagrov.repository.api;

import com.mbagrov.dto.Post;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */

public interface PostRepository {

    public List<Post> findAll();

}
