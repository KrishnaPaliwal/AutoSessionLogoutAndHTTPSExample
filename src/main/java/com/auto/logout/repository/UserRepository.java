package com.auto.logout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auto.logout.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}