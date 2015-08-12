package by.academy.alekhno.web.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.command.CommandFactory;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String commandType = req.getParameter(Bundle.getResource("key.command")).toUpperCase();
        Command command = CommandFactory.getCommand(commandType);
        String page = command.execute(req, resp);
        if (!Bundle.getResource("key.redirect").equals(page)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}