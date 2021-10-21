package by.bsuir.util;

public class Validator {
    private final static String REGEX_LOGIN = "^([\\w_\\-\\.])+@[\\w\\-\\.]+\\.([A-Za-z]{2,5})$";
    private final static int MAX_STRING_LENGTH = 45;

    public boolean validateEmail(String login){
        return login != null && !login.equals("") && login.matches(REGEX_LOGIN);
    }
}
