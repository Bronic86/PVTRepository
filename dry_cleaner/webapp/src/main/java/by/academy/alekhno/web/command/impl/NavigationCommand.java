package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;


public class NavigationCommand implements Command {

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return Bundle.getResource("to.page.navigation");
	}

	
}
