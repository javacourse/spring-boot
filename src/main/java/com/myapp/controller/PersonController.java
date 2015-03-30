package com.myapp.controller;

import com.myapp.model.Person;
import com.myapp.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person/")
public class PersonController
{

	private static final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET, value = "list")
	public ModelAndView listPeople()
	{
		logger.debug("Received request to list persons");
		ModelAndView mav = new ModelAndView();
		List<Person> people = personService.findAll();
		logger.debug("Person Listing count = " + people.size());
		mav.addObject("people", people);
		mav.setViewName("list");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "new")
	public ModelAndView addPerson()
	{
		logger.debug("Received request to add new person ");
		ModelAndView mav = new ModelAndView();
		Person person = new Person();
		mav.addObject("person", person);
		mav.setViewName("add");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "edit")
	public ModelAndView editPerson(@RequestParam(value = "id") Long id)
	{
		logger.debug("Received request to edit person id : " + id);
		ModelAndView mav = new ModelAndView();
		Person person = personService.getById(id);
		mav.addObject("person", person);
		mav.setViewName("edit");
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = { "new", "edit" })
	public String savePerson(@ModelAttribute("person") Person person)
	{
		logger.debug("Received postback on person " + person);
		personService.saveOrUpdate(person);
		return "redirect:list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "delete")
	public String deletePerson(@RequestParam(value = "id") Long id)
	{
		personService.deletePerson(id);
		return "redirect:list";
	}

    @RequestMapping(method = RequestMethod.GET, value = "search")
    public ModelAndView searchPerson(@RequestParam Map<String, String> searchParams){
        ModelAndView mav = new ModelAndView();
        List<Person> people = personService.searchByParams(searchParams);
        mav.addObject("people", people);
        mav.addObject("searchParams", searchParams);
        mav.setViewName("list");
        return mav;
    }

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception ex)
	{

		logger.warn(ClassUtils.getShortName(ex.getClass()) + " -- "
				+ ex.getMessage());
		return ex.getMessage();
	}
}
