package ru.fokin.spring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.fokin.spring.model.Product;
import ru.fokin.spring.service.UserService;

import java.math.BigDecimal;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserService userService;

    public UserCommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser("Илья");
        userService.createUser("Олег");

        userService.getAllUsers().forEach(System.out::println);

        userService.updateUser(1L, "Жора");

        userService.getAllUsers().forEach(System.out::println);

        userService.createProduct(1L, "123456789", new BigDecimal("1000.00"), "счет");
        userService.createProduct(1L, "987654321", new BigDecimal("500.00"), "карта");

        userService.getProductsByUserId(1L).forEach(System.out::println);

        Product product = userService.getProductById(1L);
        System.out.println("Product by ID: " + product);

        userService.deleteUser(12L);

        userService.getAllUsers().forEach(System.out::println);
    }
}
