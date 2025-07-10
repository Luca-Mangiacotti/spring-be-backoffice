package org.lessons.java.spring_be_backoffice.repository;

import java.util.Optional;

import org.lessons.java.spring_be_backoffice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
