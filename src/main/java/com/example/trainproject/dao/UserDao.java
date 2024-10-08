package com.example.trainproject.dao;

import com.example.trainproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}
