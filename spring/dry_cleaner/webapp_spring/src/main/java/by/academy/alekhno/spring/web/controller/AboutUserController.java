package by.academy.alekhno.spring.web.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

@Controller
// @Secured("ROLE_USER")
@RequestMapping("/user")
public class AboutUserController {

	@Autowired
	private UserService userSrvice;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toAboutUser() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String login = userDetail.getUsername();
		User userInformation = userSrvice.getUserByLogin(login);
		// Role is empty
		userInformation.setRoles(new HashSet<Role>());
		modelAndView.addObject("userInformation", userInformation);
		modelAndView.setViewName("about_user");
		return modelAndView;
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ModelAndView changeUserInformation(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exist", model.containsAttribute("userInformation"));
		modelAndView.setViewName("change_user_inf");
		return modelAndView;
	}
}
