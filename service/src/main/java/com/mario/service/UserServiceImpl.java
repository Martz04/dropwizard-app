package com.mario.service;

import com.mario.bundles.db.user.User;
import com.mario.bundles.db.user.UserDao;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addUser(String fullName, String jobTitle) {
        userDao.insert(fullName, jobTitle);
    }
}
