package ru.fokin.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fokin.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
