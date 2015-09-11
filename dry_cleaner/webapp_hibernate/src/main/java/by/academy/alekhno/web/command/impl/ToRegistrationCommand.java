package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.alekhno.web.command.Command;

public class ToRegistrationCommand implements Command {

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "WEB-INF/pages/registration.jsp";
	}

}
