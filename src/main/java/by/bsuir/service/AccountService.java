package by.bsuir.service;

import by.bsuir.entity.Account;
import by.bsuir.exception.ServiceException;

import java.util.Map;

public interface AccountService {
    boolean checkLoginAndPassword(String login, String password) throws ServiceException;
    boolean checkLogin(String login) throws ServiceException;
    Account registerUser(Map<String,String> map) throws ServiceException;
    boolean changePassword(Account account, String newPassword) throws  ServiceException;
    boolean changeNumber(Account account, String newNumber) throws  ServiceException;
    boolean deleteAccount(Account account) throws ServiceException;
    Account findByLogin(String login) throws ServiceException;
}
