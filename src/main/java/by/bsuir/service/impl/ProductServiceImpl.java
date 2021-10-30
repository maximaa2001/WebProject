package by.bsuir.service.impl;

import by.bsuir.dao.Transaction;
import by.bsuir.dao.impl.ProductDaoImpl;
import by.bsuir.entity.Product;
import by.bsuir.exception.DaoException;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.ProductService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {
    private final static Logger logger = Logger.getLogger(ProductServiceImpl.class);
    private static ProductServiceImpl instance;
    private final static Transaction transaction = new Transaction();
    private final static ProductDaoImpl productDao = ProductDaoImpl.getInstance();

    private ProductServiceImpl(){}

    public static ProductServiceImpl getInstance(){
        if(instance == null){
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Product> findAllProduct() throws ServiceException {
        List<Product> products;
        transaction.startNoTransaction(productDao);
        try{
            products = productDao.findAll();
        }catch (DaoException e){
            logger.log(Level.ERROR, "Error while finding all products", e);
            throw new ServiceException(e);
        }finally {
            transaction.endNoTransaction(productDao);
        }
        return products;
    }

    @Override
    public Set<Product> findAllProductByUserId(long id) throws ServiceException {
        Set<Product> products;
        transaction.startNoTransaction(productDao);
        try {
            products = productDao.getAllProductsByUserId(id);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Error while finding all products by user id", e);
            throw new ServiceException(e);
        }finally {
            transaction.endNoTransaction(productDao);
        }
        return products;
    }

    @Override
    public boolean createProduct(Product product) throws ServiceException {
        boolean result;
        transaction.startNoTransaction(productDao);
        try{
            result = productDao.create(product);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Error while creating product", e);
            throw new ServiceException(e);
        }finally {
            transaction.endNoTransaction(productDao);
        }
        return result;
    }

    @Override
    public boolean deleteProductById(long id) throws ServiceException {
        boolean result;
        transaction.startNoTransaction(productDao);
        try{
            result = productDao.deleteById(id);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Error while deleting product", e);
            throw new ServiceException(e);
        }finally {
            transaction.endNoTransaction(productDao);
        }
        return result;
    }

    @Override
    public boolean updateProduct(Product product) throws ServiceException {
        boolean result;
        transaction.startNoTransaction(productDao);
        try{
            result = productDao.update(product);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Error while updating product", e);
            throw new ServiceException(e);
        }finally {
            transaction.endNoTransaction(productDao);
        }
        return result;
    }
}
