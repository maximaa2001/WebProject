package by.bsuir.command.userCommand;

import by.bsuir.command.Command;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;
import by.bsuir.command.TransitionType;

public class GoToCreateProductCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        response.setPath("/jsp/createProduct.jsp");
        response.setTypeTransition(TransitionType.FORWARD);
        return response;
    }
}
