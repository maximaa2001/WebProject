package by.bsuir.command;

import by.bsuir.command.common.*;
import by.bsuir.command.userCommand.CreateProductCommand;
import by.bsuir.command.userCommand.GoToCreateProductCommand;
import by.bsuir.command.userCommand.ShowAllUserProductCommand;

public enum CommandType {
    GO_TO_LOG_IN(new GoToLogInCommand()),
    GO_TO_MAIN(new GoToMainCommand()),
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),
    GO_TO_REG_IN(new GoToRegistrationCommand()),
    GO_TO_CREATE_PRODUCT(new GoToCreateProductCommand()),
    REG_IN(new RegistrationCommand()),
    FIND_USER_PRODUCT(new ShowAllUserProductCommand()),
    CREATE_PRODUCT(new CreateProductCommand()),
    SHOW_CATALOG(new ShowCatalogCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
