package com.mbagrov.service.impl;

import com.mbagrov.dto.Post;
import com.mbagrov.repository.api.IPostRepository;
import com.mbagrov.service.api.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Odour on 27.03.2015.
 */
@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void saveOrUpdate(Post post) {
        postRepository.saveOrUpdate(post);
    }

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public boolean isExistById(Long id) {
        return postRepository.isExistById(id);
    }
}
