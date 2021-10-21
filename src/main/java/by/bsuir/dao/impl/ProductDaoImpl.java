package by.bsuir.dao.impl;

import by.bsuir.dao.ProductDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Admin;
import by.bsuir.entity.Product;
import by.bsuir.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private static ProductDaoImpl instance;
    private final static String SQL_LIST_PRODUCTS_BY_USER = "SELECT * FROM product WHERE product.User_id = ?";
    private final static String SQL_FIND_ALL = "SELECT product.id, product.name, product.price FROM product";
    private final static String SQL_FIND_BY_ID = "SELECT product.id, product.name, product.price FROM product WHERE product.id = ?";
    private final static String SQL_CREATE = "INSERT INTO product (product.name, product.price, product.User_id) VALUES (?,?,?)";
    private final static String SQL_DELETE = "DELETE FROM product WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE product SET product.name = ?, product.price = ? WHERE product.id = ?";

    private ProductDaoImpl() {
    }

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                list.add(product);
            }
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Product findById(long id) throws DaoException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
            return product;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    public boolean create(Product entity) throws DaoException {
        long userId = entity.getUser().getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean update(Product entity) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Set<Product> getAllProductsByUserId(long id) throws DaoException {
        Set<Product> set = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LIST_PRODUCTS_BY_USER)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product.id"));
                product.setName(resultSet.getString("product.name"));
                product.setPrice(resultSet.getInt("product.price"));
                set.add(product);
            }
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return set;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("product.id"));
        product.setName(resultSet.getString("product.name"));
        product.setPrice(resultSet.getInt("product.price"));
        return product;
    }
}
