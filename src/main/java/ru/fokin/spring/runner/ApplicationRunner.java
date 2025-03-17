package ru.fokin.spring.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.fokin.spring.model.User;
import ru.fokin.spring.service.ProductService;
import ru.fokin.spring.service.UserService;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ApplicationRunner(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = userService.createUser("Сергей");
        User user2 = userService.createUser("Екатерина");

        productService.createProduct("ACC123456", 1000.0, "счет", user1.getId());
        productService.createProduct("CARD654321", 500.0, "карта", user1.getId());
        productService.createProduct("ACC987654", 2000.0, "счет", user2.getId());

        System.out.println("Products of user1:");
        productService.getProductsByUserId(user1.getId()).forEach(System.out::println);

        System.out.println("Product by ID:");
        System.out.println(productService.getProductById(1L));
    }
}
