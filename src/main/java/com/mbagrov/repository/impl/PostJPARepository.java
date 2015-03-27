package com.mbagrov.repository.impl;

import com.mbagrov.dto.Post;
import com.mbagrov.repository.api.IPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Odour on 27.03.2015.
 */
@Repository
@Transactional(readOnly = true)
public class PostJPARepository extends AbstractJPARepository<Post, Long> implements IPostRepository{
}
