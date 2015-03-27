package com.mbagrov.controller;
import com.mbagrov.dto.User;
import com.mbagrov.repository.api.IUserRepository;
import com.mbagrov.repository.crud.PostCrudRepository;
import com.mbagrov.repository.crud.UserCrudRepository;
import com.mbagrov.service.api.IPostService;
import com.mbagrov.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private IUserService userService;
    private IPostService postService;

    @Autowired
    public HelloController(IUserService userService, IPostService postService) {
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
        Iterable users = userService.findAll();
        Iterable posts = postService.findAll();
        ModelAndView modelAndView = new ModelAndView("secret");
        modelAndView.addObject("users", users);
        modelAndView.addObject("posts", posts);

        return modelAndView;
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        logger.debug("Received request to show login page");
        return "login";
    }
}
