package com.mbagrov.service.api;

import com.mbagrov.dto.Post;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IPostService {

    public List<Post> findAll();

    public void saveOrUpdate(Post post);

    public Post getById(Long id);

    public boolean isExistById(Long id);

}
