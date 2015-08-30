package by.academy.alekhno.web.command;

import by.academy.alekhno.web.command.impl.AddOrderCommand;
import by.academy.alekhno.web.command.impl.AuthorizationCommand;
import by.academy.alekhno.web.command.impl.ChangeOrderCommand;
import by.academy.alekhno.web.command.impl.DeleteOrderCommand;
import by.academy.alekhno.web.command.impl.LogOutCommand;
import by.academy.alekhno.web.command.impl.NavigationCommand;
import by.academy.alekhno.web.command.impl.OrdersCommand;
import by.academy.alekhno.web.command.impl.PriceListCommand;
import by.academy.alekhno.web.command.impl.RegistrationCommand;
import by.academy.alekhno.web.command.impl.ToAuthorithationCommand;
import by.academy.alekhno.web.command.impl.ToRegistrationCommand;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOG_OUT(new LogOutCommand()),
    NAVIGATION(new NavigationCommand()),
    PRICE_LIST(new PriceListCommand()),
    CHANGE_ORDER(new ChangeOrderCommand()),
    TO_AUTHORIZATION(new ToAuthorithationCommand()),
    TO_REGISTRATION(new ToRegistrationCommand()),
    ADD_ORDER(new AddOrderCommand()),
    ORDERS(new OrdersCommand()),
    DELETE_ORDER(new DeleteOrderCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
