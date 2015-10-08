package by.academy.alekhno.spring.web.controller;

import java.util.HashSet;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

@Controller
public class RegistrationController {
	private Logger logger = Logger.getLogger(RegistrationController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrat(Model model, @ModelAttribute("userForm") @Valid User userForm,
			BindingResult result) {

		if (result.hasErrors()) {
			return "registration";
		}

		// add registration
		logger.info(userForm);

		User user = new User();
		user.setLogin(userForm.getLogin());
		user.setPassword(userForm.getPassword());
		user.setFirstName(userForm.getFirstName());
		user.setSecondName(userForm.getSecondName());
		user.setTelephone(userForm.getTelephone());
		user.setRoles(new HashSet<Role>());

		User userRegistrate = userService.registration(user);
		if (userRegistrate == null) {
			model.addAttribute("errorLoginExist", "Sorry. Such login exist.");
			return "registration";
		} else {
			return "registration_success";
		}
	}
}
