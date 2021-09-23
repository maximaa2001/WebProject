package by.bsuir.dao;

import by.bsuir.entity.Account;
import by.bsuir.exception.DaoException;

public interface AccountDao extends BaseDao<Account>{
    Account findByLogin(String login) throws DaoException;
}
