package by.bsuir.dao;

import by.bsuir.entity.Product;

import java.util.Set;

public interface ProductDao extends BaseDao<Product> {
    Set<Product> getAllProductsByUserId(long id);
}
