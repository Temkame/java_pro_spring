package ru.fokin.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.fokin.spring.model.User;
import ru.fokin.spring.service.UserService;

@ComponentScan
public class Main {

        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

            UserService userService = context.getBean(UserService.class);

            User user = new User();
            user.setUsername("Олег");
            userService.createUser(user);

            User retrievedUser = userService.getUserById(1L);
            if (retrievedUser != null) {
                System.out.println("Получили пользователя: " + retrievedUser.getUsername());
            } else {
                System.out.println("Пользователь с данным ID не найден.");
            }

            System.out.println("Все пользователи:");
            userService.getAllUsers().forEach(u -> System.out.println(u.getUsername()));

            retrievedUser.setUsername("Артем_дубликат3");
            userService.updateUser(retrievedUser);

            userService.deleteUser(retrievedUser.getId());
        }
}