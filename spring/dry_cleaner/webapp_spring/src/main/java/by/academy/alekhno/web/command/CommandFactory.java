package by.academy.alekhno.web.command;

public class CommandFactory {

    private CommandFactory() {
    }

    public static Command getCommand(String command) {
        return CommandType.valueOf(command).getCommand();
    }
}
