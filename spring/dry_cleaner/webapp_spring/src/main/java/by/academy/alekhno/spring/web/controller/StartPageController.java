package by.academy.alekhno.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.vo.User;

@Controller
public class StartPageController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toStartPage(Model map) {
		return "index";
	}

	@RequestMapping(value = "/authorized", method = RequestMethod.GET)
	public String toStartPageAfterAuthorization(Model map, HttpServletRequest request) {
		// add User to session after authorization
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		User user = userService.getUserByLogin(login);
		request.getSession().setAttribute("userFirstName",
				user.getFirstName() + " " + user.getSecondName());
		return "index";
	}

}
