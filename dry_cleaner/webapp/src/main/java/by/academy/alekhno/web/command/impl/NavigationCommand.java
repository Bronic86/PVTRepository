package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;


public class NavigationCommand implements Command {
	private static final Logger logger = Logger.getLogger(AuthorizationCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute NavigationCommand");
		return Bundle.getResource("to.page.navigation");
	}

	
}
