package com.personal_bank_app.service;

import com.personal_bank_app.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
    void deleteUser(String userId);
    User updateUser(User user);
}
