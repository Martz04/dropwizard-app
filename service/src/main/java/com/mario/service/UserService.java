package com.mario.service;

import com.mario.bundles.db.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();
    Optional<User> getUserById(int id);
    void addUser(String fullName, String jobTitle);
}
