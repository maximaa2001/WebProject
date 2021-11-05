package by.bsuir.command.common;

import by.bsuir.command.*;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.AccountServiceImpl;
import by.bsuir.service.impl.UserServiceImpl;
import by.bsuir.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements Command {
    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);
    private final static Validator validator = new Validator();

    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        String login = requestCommand.getRequestParameter(Constant.PARAMETER_LOGIN);
        String password = requestCommand.getRequestParameter(Constant.PARAMETER_PASSWORD);
        String password_repeat = requestCommand.getRequestParameter(Constant.PARAMETER_PASSWORD_REPEAT);
        String number = requestCommand.getRequestParameter(Constant.PARAMETER_NUMBER);
        AccountServiceImpl accountService = AccountServiceImpl.getInstance();
        Map<String, String> userParam = new HashMap<>();
        userParam.put(Constant.PARAMETER_LOGIN, login);
        userParam.put(Constant.PARAMETER_PASSWORD, password);
        userParam.put(Constant.PARAMETER_PASSWORD_REPEAT, password_repeat);
        userParam.put(Constant.PARAMETER_NUMBER, number);
        userParam = validator.validateRegisterUser(userParam);
        if (userParam.get(Constant.PARAMETER_REG_ERROR_LOGIN) != null) {
            requestCommand.addRequestAttribute(Constant.PARAMETER_REG_ERROR_LOGIN, userParam.get(Constant.PARAMETER_REG_ERROR_LOGIN));
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/reg_in.jsp");
        }else {
            requestCommand.addRequestAttribute(Constant.PARAMETER_REMEMBER_LOGIN,login);
        }
        if (userParam.get(Constant.PARAMETER_REG_ERROR_PASSWORD) != null) {
            requestCommand.addRequestAttribute(Constant.PARAMETER_REG_ERROR_PASSWORD, userParam.get(Constant.PARAMETER_REG_ERROR_PASSWORD));
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/reg_in.jsp");
        }
        if (userParam.get(Constant.PARAMETER_REG_ERROR_NUMBER) != null) {
            requestCommand.addRequestAttribute(Constant.PARAMETER_REG_ERROR_NUMBER, userParam.get(Constant.PARAMETER_REG_ERROR_NUMBER));
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/reg_in.jsp");
        }else {
            requestCommand.addRequestAttribute(Constant.PARAMETER_REMEMBER_NUMBER,number);
        }
        if (userParam.get(Constant.PARAMETER_ERROR_REPEAT_PASSWORD) != null) {
            requestCommand.addRequestAttribute(Constant.PARAMETER_ERROR_REPEAT_PASSWORD, userParam.get(Constant.PARAMETER_ERROR_REPEAT_PASSWORD));
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/reg_in.jsp");
        }
        if (userParam.get(Constant.PARAMETER_REG_ERROR_LOGIN) == null && userParam.get(Constant.PARAMETER_REG_ERROR_PASSWORD) == null
                && userParam.get(Constant.PARAMETER_REG_ERROR_NUMBER) == null && userParam.get(Constant.PARAMETER_ERROR_REPEAT_PASSWORD) == null) {
            try {
                if (accountService.checkLogin(login)) {
                    requestCommand.addRequestAttribute(Constant.PARAMETER_REG_WARN_LOGIN, "User with this login also exist");
                } else {
                    accountService.registerUser(userParam);
                    requestCommand.addRequestAttribute(Constant.PARAMETER_SUCCESS_REG, "Registration completed successfully");
                }
                requestCommand.removeRequestAttribute(Constant.PARAMETER_REMEMBER_LOGIN);
                requestCommand.removeRequestAttribute(Constant.PARAMETER_REMEMBER_NUMBER);
                response.setTypeTransition(TransitionType.FORWARD);
                response.setPath("/jsp/reg_in.jsp");
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while Registration command", e);
                response.setTypeTransition(TransitionType.REDIRECT);
                response.setPath("/jsp/error.jsp");
            }
        }
        return response;
    }
}
