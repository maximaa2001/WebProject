package by.bsuir.dao;

import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;

public interface UserDao extends BaseDao<User>{
    User getUserByProductId(long id) throws DaoException;
    boolean setUserApprove(long id) throws DaoException;
}
