package by.bsuir.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();
    T findById(long id);
    boolean create(T entity);
    boolean deleteById(long id);
    boolean update(T entity);
}
