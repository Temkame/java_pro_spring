package ru.fokin.spring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.fokin.spring.service.UserService;

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

        userService.deleteUser(2L);

        userService.getAllUsers().forEach(System.out::println);
    }
}
