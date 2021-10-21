package by.bsuir.dao;

import by.bsuir.entity.Product;
import by.bsuir.exception.DaoException;

import java.util.Set;

public interface ProductDao  {
    Set<Product> getAllProductsByUserId(long id) throws DaoException;
}
