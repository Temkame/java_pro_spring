package ru.fokin.spring.service;

import org.springframework.stereotype.Service;
import ru.fokin.spring.model.Product;
import ru.fokin.spring.model.User;
import ru.fokin.spring.repository.ProductRepository;
import ru.fokin.spring.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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

    public void createProduct(Long userId, String accountNumber, BigDecimal balance, String productType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Не найден user"));
        Product product = new Product(accountNumber, balance, productType, user);
        productRepository.save(product);
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Не найден product"));
    }
}