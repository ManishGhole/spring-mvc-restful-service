package com.samples.spring.mvc.rest.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello/{name}", method = RequestMethod.GET)
   public String printHello(@PathVariable String name, ModelMap model) {
      model.addAttribute("message", "Hello " + name + "!!");
      return "welcome";
   }
	
}
