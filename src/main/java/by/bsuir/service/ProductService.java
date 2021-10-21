package by.bsuir.service;

import by.bsuir.entity.Product;
import by.bsuir.entity.User;
import by.bsuir.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> findAllProduct() throws ServiceException;
    Set<Product> findAllProductByUserId(long id) throws ServiceException;
    boolean createProduct(Product product) throws ServiceException;
    boolean deleteProductById(long id) throws ServiceException;
    boolean updateProduct(Product product) throws ServiceException;
}
