package by.academy.alekhno.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartPageController {

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(ModelAndView map) {

		return "registration";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toStartPage() {
		ModelAndView map = new ModelAndView();
		return "index";
	}

}
