package by.bsuir.command.common;

import by.bsuir.command.Command;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;
import by.bsuir.command.TransitionType;

public class GoToRegistrationCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        response.setPath("/jsp/reg_in.jsp");
        response.setTypeTransition(TransitionType.FORWARD);
        return response;
    }
}
