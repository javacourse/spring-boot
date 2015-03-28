package com.mbagrov.service.api;

import com.mbagrov.dto.Post;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
public interface IPostService {

    public List<Post> findAll();

    public void saveOrUpdate(Post post);

    public Post getById(Long id) throws Exception;

    public boolean isExistById(Long id);

    public void deleteById(Long id) throws Exception;

    public void delete(Post post) throws Exception;

}
