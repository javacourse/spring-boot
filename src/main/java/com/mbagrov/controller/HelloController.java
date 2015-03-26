package com.mbagrov.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Odour on 25.03.2015.
 */

@Controller
@RequestMapping(value = "/")
public class HelloController {

    @RequestMapping(value =  {"/", "home", "index"}, method = RequestMethod.GET)
    public String hello() {
        return "home";
    }

    @RequestMapping(value = "home2", method = RequestMethod.GET)
    public String hello2() {
        return "home2";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return "login";
    }
}
