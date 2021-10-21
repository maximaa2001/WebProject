package by.bsuir.dao;

import by.bsuir.entity.Account;
import by.bsuir.exception.DaoException;

public interface AccountDao{
    boolean findByLogin(String login) throws DaoException;
    boolean findByLoginAndPassword(String login, String password) throws DaoException;
    long findIdByLogin(String login) throws DaoException;
}
