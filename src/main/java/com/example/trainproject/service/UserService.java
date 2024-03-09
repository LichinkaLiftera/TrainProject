package com.example.trainproject.service;

import com.example.trainproject.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(String name);
    User addUser(User user);
    User updateUser(User user);
    Optional<User>getUserById(long id);
    List<User> getAllUsers();
    void deleteUser(long id);

}
