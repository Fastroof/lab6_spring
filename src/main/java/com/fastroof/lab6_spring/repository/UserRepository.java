package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    List<User> getUsers();
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void save(User user);
}