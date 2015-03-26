package com.mbagrov.service.impl;

import com.mbagrov.dto.Post;
import com.mbagrov.repository.api.PostRepository;
import com.mbagrov.service.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Odour on 26.03.2015.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
