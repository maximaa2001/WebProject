package by.bsuir.command.common;

import by.bsuir.command.Command;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;
import by.bsuir.command.TransitionType;

public class GoToLogInCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        return new ResponseCommand(TransitionType.FORWARD,"/jsp/log_in.jsp");
    }
}
