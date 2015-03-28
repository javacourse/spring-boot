package com.mbagrov.controller;

import com.mbagrov.dto.Post;
import com.mbagrov.service.api.IPostService;
import com.mbagrov.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public ModelAndView hello2() {
        logger.debug("Received request to show secret page");

        Iterable posts = postService.findAll();
        ModelAndView modelAndView = new ModelAndView("blog");

        modelAndView.addObject("posts", posts);

        return modelAndView;
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        logger.debug("Received request to show login page");
        return "login";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex) {

        logger.warn(ClassUtils.getShortName(ex.getClass()) + " -- "
                + ex.getMessage());

        return ex.getMessage();
    }
}
