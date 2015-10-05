package by.academy.alekhno.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.UserService;

@Controller
public class AuthorizationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
	public String toAuthorithation() {

		return "authorization";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {

		return "login";
	}

	// @RequestMapping(value = "/authorization", method = RequestMethod.POST)
	// public String toPriceList(ModelAndView map) {
	//
	// return "index";
	// }

	// @RequestMapping(value = "/authorization", method = RequestMethod.GET)
	// public ModelAndView login(@RequestParam(value = "error", required =
	// false) String error,
	// @RequestParam(value = "logout", required = false) String logout,
	// HttpServletRequest request) {
	//
	// ModelAndView model = new ModelAndView();
	// if (error != null) {
	// model.addObject("error", getErrorMessage(request,
	// "SPRING_SECURITY_LAST_EXCEPTION"));
	// }
	//
	// if (logout != null) {
	// model.addObject("msg", "You've been logged out successfully.");
	// }
	// model.setViewName("authorization");
	//
	// return model;
	//
	// }
	//
	// // customize the error message
	// private String getErrorMessage(HttpServletRequest request, String key) {
	//
	// Exception exception = (Exception) request.getSession().getAttribute(key);
	//
	// String error = "";
	// if (exception instanceof BadCredentialsException) {
	// error = "Invalid username and password!";
	// } else if (exception instanceof LockedException) {
	// error = exception.getMessage();
	// } else {
	// error = "Invalid username and password!";
	// }
	//
	// return error;
	// }

	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public String logoutPage(HttpServletRequest request, HttpServletResponse
	// response) {
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (auth != null) {
	// new SecurityContextLogoutHandler().logout(request, response, auth);
	// }
	// return "redirect:/login?logout";
	// }
	//
	// @RequestMapping(value = "/403", method = RequestMethod.GET)
	// public ModelAndView accesssDenied() {
	//
	// ModelAndView model = new ModelAndView();
	//
	// // check if user is login
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (!(auth instanceof AnonymousAuthenticationToken)) {
	// UserDetails userDetail = (UserDetails) auth.getPrincipal();
	//
	// model.addObject("username", userDetail.getUsername());
	//
	// }
	//
	// model.setViewName("403");
	// return model;
	//
	// }

}
