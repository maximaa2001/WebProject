package by.bsuir.command.common;

import by.bsuir.command.*;
import by.bsuir.entity.Account;
import by.bsuir.entity.User;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.AccountServiceImpl;
import by.bsuir.service.impl.UserServiceImpl;
import by.bsuir.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogInCommand implements Command {
    private final static Logger logger = Logger.getLogger(LogInCommand.class);
    private final static Validator validator = new Validator();

    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        String login = requestCommand.getRequestParameter(Constant.PARAMETER_LOGIN);
        String password = requestCommand.getRequestParameter(Constant.PARAMETER_PASSWORD);
        AccountServiceImpl accountService = AccountServiceImpl.getInstance();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if(accountService.checkLoginAndPassword(login, password)){
                Account account = accountService.findByLogin(login);
                requestCommand.addSessionAttribute(Constant.ATTRIBUTE_ACCOUNT,account);
                User user = userService.findUserByAccountId(account.getId());
                if(user == null){
                    requestCommand.addSessionAttribute(Constant.ATTRIBUTE_ROLE,"admin");
                    response.setTypeTransition(TransitionType.FORWARD);
                    response.setPath("/jsp/main.jsp");
                }else {
                    if(user.isApproved()){
                        requestCommand.addSessionAttribute(Constant.ATTRIBUTE_ROLE,"user");
                        requestCommand.addSessionAttribute(Constant.ATTRIBUTE_USER,user);
                        response.setTypeTransition(TransitionType.FORWARD);
                        response.setPath("/jsp/main.jsp");
                    }else {
                        requestCommand.addRequestAttribute(Constant.PARAMETER_UN_APPROVE,"Wait for the admin to approve your profile");
                        response.setTypeTransition(TransitionType.FORWARD);
                        response.setPath("/jsp/log_in.jsp");
                    }
                }
            }else {
                if(accountService.checkLogin(login)){
                    requestCommand.addRequestAttribute(Constant.PARAMETER_INCORRECT_PASSWORD,"Incorrect password");
                }else {
                    requestCommand.addRequestAttribute(Constant.PARAMETER_INCORRECT_LOGIN,"Incorrect login");
                }
                response.setTypeTransition(TransitionType.FORWARD);
                response.setPath("/jsp/log_in.jsp");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"Error while login command", e);
            response.setTypeTransition(TransitionType.REDIRECT);
            response.setPath("/jsp/error.jsp");
        }
        return response;
    }
}
