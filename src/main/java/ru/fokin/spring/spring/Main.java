package ru.fokin.spring.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.fokin.spring.spring.service.UserService;

import java.sql.SQLException;

@ComponentScan
public class Main {

        public static void main(String[] args) throws SQLException {

            ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

            UserService userService = context.getBean(UserService.class);

            userService.createUser("Артем");

            System.out.println(userService.getAllUsers());

            userService.updateUser(1L, "Илья");

            System.out.println(userService.getAllUsers());

            userService.deleteUser(1L);

        }
}