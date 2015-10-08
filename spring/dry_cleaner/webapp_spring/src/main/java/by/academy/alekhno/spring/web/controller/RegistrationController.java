package by.academy.alekhno.spring.web.controller;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

@Controller
public class RegistrationController {
	private Logger logger = Logger.getLogger(RegistrationController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView toRegistration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userForm", new User());
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrat(Model model, @ModelAttribute("userForm") User userForm) {
		// add registration
		logger.info(userForm);
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		user.setLogin(userForm.getLogin());
		user.setPassword(userForm.getPassword());
		user.setFirstName(userForm.getFirstName());
		user.setSecondName(userForm.getSecondName());
		user.setTelephone(userForm.getTelephone());
		user.setRoles(new HashSet<Role>());

		User userRegistrate = userService.registration(user);
		if (userRegistrate == null) {
			modelAndView.addObject("errorLoginExist", "Sorry. Such login exist.");
			modelAndView.setViewName("registration");
		} else {
			modelAndView.setViewName("registration_success");
		}
		return modelAndView;
	}
}
