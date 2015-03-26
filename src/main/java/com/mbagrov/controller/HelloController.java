package com.mbagrov.controller;
import com.mbagrov.dto.Person;
import com.mbagrov.repository.api.PersonRepository;
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

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value =  {"/", "home", "index"}, method = RequestMethod.GET)
    public String hello() {
        logger.debug("Received request to show main page");
        return "home";
    }

    @RequestMapping(value = "home2", method = RequestMethod.GET)
    public ModelAndView hello2() {
      //  List<Person> persons = personRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("secret");
      //  modelAndView.addObject("persons", persons);

        return modelAndView;
       // model.addAttribute("persons", persons);
       // return "home2";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return "login";
    }
}
