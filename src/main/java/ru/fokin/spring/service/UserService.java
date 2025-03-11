package ru.fokin.spring.service;

import org.springframework.stereotype.Service;
import ru.fokin.spring.model.User;
import ru.fokin.spring.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String userName) {
        User user = new User(userName);
        userRepository.save(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.findAll();
    }

    public void updateUser(Long id, String userName) throws SQLException {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        userRepository.save(user);
    }

    public void deleteUser(Long id) throws SQLException {
        userRepository.deleteById(id);
    }
}
