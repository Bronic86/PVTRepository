package by.academy.alekhno.web.command;

import by.academy.alekhno.web.command.impl.AuthorizationCommand;
import by.academy.alekhno.web.command.impl.LogOutCommand;
import by.academy.alekhno.web.command.impl.NavigationCommand;
import by.academy.alekhno.web.command.impl.PriceListCommand;
import by.academy.alekhno.web.command.impl.RegistrationCommand;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOG_OUT(new LogOutCommand()),
    NAVIGATION(new NavigationCommand()),
    PRICE_LIST(new PriceListCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
