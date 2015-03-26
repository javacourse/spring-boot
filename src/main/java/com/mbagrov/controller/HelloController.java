package com.mbagrov.controller;
import com.mbagrov.dto.Post;
import com.mbagrov.dto.User;
import com.mbagrov.service.api.PostService;
import com.mbagrov.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Odour on 25.03.2015.
 */

@Controller
@RequestMapping(value = "/")
public class HelloController {

    private static final Logger logger = LoggerFactory
            .getLogger(HelloController.class);

    private UserService userService;
    private PostService postService;

    @Autowired
    public HelloController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value =  {"/", "home", "index"}, method = RequestMethod.GET)
    public String hello() {
        logger.debug("Received request to show main page");
        return "home";
    }

    @RequestMapping(value = "secret", method = RequestMethod.GET)
    public ModelAndView hello2() {
        logger.debug("Received request to show secret page");
        List<User> users = userService.findAll();
        List<Post> posts = postService.findAll();
        ModelAndView modelAndView = new ModelAndView("secret");
        modelAndView.addObject("users", users);
        modelAndView.addObject("posts", posts);

        return modelAndView;
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return "login";
    }
}
