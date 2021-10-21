package by.bsuir.service;

import by.bsuir.entity.User;
import by.bsuir.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    User findUserById(long id) throws ServiceException;
    User findUserByProductId(long id) throws ServiceException;
    User findUserByAccountId(long id) throws ServiceException;
    List<User> findAllUnApprovedUser() throws ServiceException;
    boolean changeStatus(long id) throws ServiceException;
}
