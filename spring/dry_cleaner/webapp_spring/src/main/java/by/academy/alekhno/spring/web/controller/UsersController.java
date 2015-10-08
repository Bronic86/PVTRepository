package by.academy.alekhno.spring.web.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.spring.web.form.AddRoleForm;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "/users")
public class UsersController {
	private Logger logger = Logger.getLogger(UsersController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String toSeeUsers(Model model) {
		Set<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
	public String toUserInformation(Model model, @PathVariable int user_id) {
		User user = userService.getUserById(user_id);
		Set<Role> roles = userService.getAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("userInformation", user);
		model.addAttribute("addRoleForm", new AddRoleForm());
		return "add_role";
	}

	@RequestMapping(value = "/user/change", method = RequestMethod.POST)
	public String AddOrder(Model model,
			@ModelAttribute(value = "addRoleForm") AddRoleForm addRoleForm, BindingResult result) {
		int user_id = Integer.parseInt(addRoleForm.getUser_id());
		int role_id = Integer.parseInt(addRoleForm.getRole_id());

		User user = new User();
		user.setId(user_id);
		Role role = new Role();
		role.setId(role_id);

		if (addRoleForm.getAdd_remove().equals("add")) {
			userService.addRoleToUser(user, role);
		} else if (addRoleForm.getAdd_remove().equals("remove")) {
			userService.removeRoleFromUser(user, role);
		}
		return "redirect:/users";
	}

}
