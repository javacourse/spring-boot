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
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.invoke.MethodType;
import java.util.Date;

/**
 * Created by Odour on 27.03.2015.
 */

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;

    private static final Logger logger = LoggerFactory
            .getLogger(PostController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPost() throws Exception {

        logger.debug("Received request to add new post");

        ModelAndView modelAndView = new ModelAndView("post_add");
        Post post = new Post();

        modelAndView.addObject("post", post);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam(value = "id") Long postId) {

        logger.debug("Received request to edit post");

        Post post = postService.getById(postId);
        ModelAndView modelAndView = new ModelAndView("post_add");
        modelAndView.addObject("post", post);

        return modelAndView;
    }

    @RequestMapping(value = {"/add", "/edit"}, method = RequestMethod.POST)
    public String savePost(/*@ModelAttribute(value = "post") Post post,*/
                            @RequestParam("topic") String topic,
                            @RequestParam("text") String text,
                            @RequestParam("id") Long id) throws Exception {

        Post post;

        if (id == null || !postService.isExistById(id)) {
            post = new Post();
            post.setDate(new Date());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.getByUsername(username);

            post.setUser(user);
        } else {
            post = postService.getById(id);
        }

        post.setTopic(topic.trim());
        post.setText(text.trim());

        postService.saveOrUpdate(post);

        return "redirect:/blog";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam(value = "id") Long id) {

        System.out.println(id);
       // postService.deleteById(id);

        Post post = postService.getById(id);
        System.out.println(post.toString());

        postService.delete(post);

        return "redirect:/blog";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex) {

        logger.warn(ClassUtils.getShortName(ex.getClass()) + " -- "
                + ex.getMessage());

        return ex.getMessage();
    }
}
