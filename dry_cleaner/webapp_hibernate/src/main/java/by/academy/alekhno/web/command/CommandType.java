package by.academy.alekhno.web.command;

import by.academy.alekhno.web.command.impl.AddOrderCommand;
import by.academy.alekhno.web.command.impl.AddRoleCommand;
import by.academy.alekhno.web.command.impl.AllUsersCommand;
import by.academy.alekhno.web.command.impl.AuthorizationCommand;
import by.academy.alekhno.web.command.impl.ChangeOrderCommand;
import by.academy.alekhno.web.command.impl.DeleteOrderCommand;
import by.academy.alekhno.web.command.impl.LogOutCommand;
import by.academy.alekhno.web.command.impl.NavigationCommand;
import by.academy.alekhno.web.command.impl.OrdersCommand;
import by.academy.alekhno.web.command.impl.PriceListCommand;
import by.academy.alekhno.web.command.impl.RegistrationCommand;
import by.academy.alekhno.web.command.impl.ToAuthorithationCommand;
import by.academy.alekhno.web.command.impl.ToErrorCommand;
import by.academy.alekhno.web.command.impl.ToRegistrationCommand;
import by.academy.alekhno.web.command.impl.UserInformationCommand;

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
    DELETE_ORDER(new DeleteOrderCommand()),
    TO_ERROR_PAGE(new ToErrorCommand()),
    ADD_ROLE(new AddRoleCommand()),
    USER_INFORMATION(new UserInformationCommand()),
    ALL_USERS(new AllUsersCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
