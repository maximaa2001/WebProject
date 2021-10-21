package by.bsuir.dao;

import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;

import java.util.List;

public interface UserDao{
    User getUserByProductId(long id) throws DaoException;
    User findUserByAccountId(long id) throws DaoException;
    List<User> findAllUnApprovedUser() throws DaoException;
    boolean setUserApprove(long id) throws DaoException;
}
