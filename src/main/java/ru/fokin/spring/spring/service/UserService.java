package ru.fokin.spring.spring.service;

import org.springframework.stereotype.Service;
import ru.fokin.spring.spring.dao.UserDao;
import ru.fokin.spring.spring.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String userName) throws SQLException {
        userDao.createUser(userName);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

   public void updateUser(Long id, String userName) throws SQLException {
        userDao.updateUser(id, userName);
    }

    public void deleteUser(Long id) throws SQLException {
        userDao.deleteUser(id);
    }
}
