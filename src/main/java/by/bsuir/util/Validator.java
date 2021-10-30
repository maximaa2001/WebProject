package by.bsuir.util;

import by.bsuir.command.Constant;

import java.util.Map;

public class Validator {
    private final static String REGEX_LOGIN = "^([\\w_\\-\\.])+@[\\w\\-\\.]+\\.([A-Za-z]{2,5})$";
    private final static String REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,40}";
    private final static String REGEX_NUMBER = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$";
    private final static int MAX_STRING_LENGTH = 45;



    public Map<String,String> validateRegisterUser(Map<String,String> map){
        String login = map.get(Constant.PARAMETER_LOGIN);
        String password = map.get(Constant.PARAMETER_PASSWORD);
        String password_repeat = map.get(Constant.PARAMETER_PASSWORD_REPEAT);
        String number = map.get(Constant.PARAMETER_NUMBER);
        if(!validateEmail(login)){
            map.put(Constant.PARAMETER_REG_ERROR_LOGIN,"Login does not match " + REGEX_LOGIN);
        }
        if(!validatePassword(password)){
            map.put(Constant.PARAMETER_REG_ERROR_PASSWORD,"Password must be more than 8 characters and " +
                    "contain at least 1 number, 1 lowercase and 1 uppercase letter");
        }
        if(!validateNumber(number)){
            map.put(Constant.PARAMETER_REG_ERROR_NUMBER,"Wrong phone number");
        }
        if(!password.equals(password_repeat)){
            map.put(Constant.PARAMETER_ERROR_REPEAT_PASSWORD,"Passwords are not the same");
        }
        return map;
    }

    private boolean validateEmail(String login){
        return login.matches(REGEX_LOGIN) && login.length() <= MAX_STRING_LENGTH;
    }

    private boolean validatePassword(String password){
        return password.matches(REGEX_PASSWORD);
    }

    private boolean validateNumber(String number){
        return number.matches(REGEX_NUMBER);
    }
}
