package com.myapp.controller;

import com.myapp.model.Address;
import com.myapp.model.Person;
import com.myapp.service.AddressService;
import com.myapp.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/address/")
public class AddressController {
    private static final Logger logger = LoggerFactory
            .getLogger(PersonController.class);
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, value = "new")
    public ModelAndView addAddress(@RequestParam(value = "personId") Long personId){
        ModelAndView mav = new ModelAndView();
        Address address = new Address();

        mav.addObject("address", address);
        mav.addObject("personId", personId);
        mav.setViewName("addressEdit");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "edit")
    public ModelAndView addAddress(@RequestParam Map<String, String> requestMap){
        ModelAndView mav = new ModelAndView();

        Address address = addressService.getById(Long.valueOf(requestMap.get("addrId")));
        mav.addObject("address", address);
        mav.addObject("personId", requestMap.get("personId"));
        mav.setViewName("addressEdit");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"new", "edit"})
    public String saveAddress(@ModelAttribute("address") Address address,
                              @RequestParam Map<String, String> requestMap){
        Person person = personService.getById(Long.valueOf(requestMap.get("personId")));
        if(requestMap.get("addrId")!=null){
            address.setId(Long.valueOf(requestMap.get("addrId")));
        }
        address.setPerson(person);
        addressService.saveOrUpdate(address);
        return "redirect:/person/edit?id="+requestMap.get("personId");
    }


    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteAddress(@RequestParam("addrId") Long addrId, @RequestParam("personId") Long personId){

        addressService.deleteAddress(addrId, personId );
        return "redirect:/person/edit?id="+personId;
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
