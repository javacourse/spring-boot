package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Odour on 25.03.2015.
 */

@Controller
@RequestMapping(value = "/")
public class Hello {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }

}
