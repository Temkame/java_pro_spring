package ru.fokin.spring.service;

import ru.fokin.spring.model.User;

import java.util.List;

public interface UserService {
    User createUser(String username);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
}