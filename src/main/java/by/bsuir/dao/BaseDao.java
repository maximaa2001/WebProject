package by.bsuir.dao;

import by.bsuir.exception.DaoException;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll() throws DaoException;
    T findById(long id) throws DaoException;
    boolean create(T entity) throws DaoException;
    boolean deleteById(long id) throws DaoException;
    boolean update(T entity) throws DaoException;
}
