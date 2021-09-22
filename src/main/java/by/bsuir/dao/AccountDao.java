package by.bsuir.dao;

import by.bsuir.entity.Account;

public interface AccountDao extends BaseDao<Account>{
    Account findByLogin(String Login);
}
