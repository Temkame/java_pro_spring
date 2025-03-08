package ru.fokin.spring.spring.dao;

import org.springframework.stereotype.Repository;
import ru.fokin.spring.spring.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private final DataSource dataSource;
    private static final String CREAT_USER_SQL = "INSERT INTO users (username) VALUES (?);";
    private static final String UPDATE_USER_SQL = "UPDATE users SET username = ? WHERE id = ?;";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String GET_ALL_USERS_SQL = "SELECT id, users.username FROM users;";

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createUser(String userName) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREAT_USER_SQL);
        preparedStatement.setString(1, userName);
        preparedStatement.executeUpdate();

    }

    public List<User> getAllUsers() throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            users.add(user);
        }

        return users;

    }

    public void updateUser(Long id, String userName) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, userName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        }

        public void deleteUser (Long id) throws SQLException {

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }
    }
