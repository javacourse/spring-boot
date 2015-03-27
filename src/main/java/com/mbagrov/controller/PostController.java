package com.mbagrov.controller;
import com.mbagrov.dto.Post;
import com.mbagrov.dto.User;
import com.mbagrov.service.api.IPostService;
import com.mbagrov.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by Odour on 27.03.2015.
 */

@Controller
@RequestMapping(value = "post")
public class PostController {

    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;

    private static final Logger logger = LoggerFactory
            .getLogger(PostController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPost() {

        logger.debug("Received request to add new post");

        ModelAndView modelAndView = new ModelAndView("post_add");
        Post post = new Post();

        modelAndView.addObject("post", post);

        return modelAndView;
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post) throws Exception {
        if (post == null) throw new Exception();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getByUsername(username);

        post.setDate(new Date());
        post.setUser(user);

        post.setText(post.getText().trim());

        postService.saveOrUpdate(post);

        return "redirect:/blog";
    }
}
