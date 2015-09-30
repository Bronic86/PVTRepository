package by.academy.alekhno.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String execute(HttpServletRequest req, HttpServletResponse resp);
    
    
}