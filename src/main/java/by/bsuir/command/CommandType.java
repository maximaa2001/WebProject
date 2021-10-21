package by.bsuir.command;

import by.bsuir.command.common.GoToLogInCommand;
import by.bsuir.command.common.LogInCommand;

public enum CommandType {
    GO_TO_LOG_IN(new GoToLogInCommand()),
    LOG_IN(new LogInCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
