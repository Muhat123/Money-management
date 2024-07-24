package com.personal_bank_app.service.impl;

import com.personal_bank_app.entity.Expenses;
import com.personal_bank_app.entity.User;
import com.personal_bank_app.repository.ExpensesRepository;
import com.personal_bank_app.repository.UserRepository;
import com.personal_bank_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExpensesRepository expensesRepository;

    public User createUser(User user) {
        List<Expenses> expenses = expensesRepository.findAll();
        User user1 = new User();
        user1.setName(user.getName());
        user1.setAccountBalance(user.getAccountBalance());
        user1.setExpenses(expenses);
        return userRepository.saveAndFlush(user1);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(User user) {
        User user1 = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setAccountBalance(user.getAccountBalance());
        return userRepository.saveAndFlush(user1);
    }
}
