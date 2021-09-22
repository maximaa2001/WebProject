package by.bsuir.dao;

import by.bsuir.entity.User;

public interface UserDao extends BaseDao<User>{
    User getUserByProductId(long id);
}
