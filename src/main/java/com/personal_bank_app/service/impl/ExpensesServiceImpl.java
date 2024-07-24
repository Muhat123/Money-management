package com.personal_bank_app.service.impl;

import com.personal_bank_app.entity.Expenses;
import com.personal_bank_app.entity.User;
import com.personal_bank_app.repository.ExpensesRepository;
import com.personal_bank_app.repository.UserRepository;
import com.personal_bank_app.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;

    @Transactional
    public Expenses addExpenses(Expenses expenses, String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (expenses.getAmount() > user.getAccountBalance()) {
            throw new IllegalArgumentException("Expenses amount exceeds account balance");
        }
        user.setAccountBalance(user.getAccountBalance() - expenses.getAmount());
        Expenses expenses1 = new Expenses();
        expenses1.setAmount(expenses.getAmount());
        expenses1.setDescription(expenses.getDescription());
        expenses1.setDate(expenses.getDate());
        expenses1.setUser(user);
        return expensesRepository.save(expenses1);
    }

    @Transactional
    public List<Expenses> getAllExpenses(String id){
        Optional<User> user = userRepository.findById(id);
        return expensesRepository.findAllByUserId(user.get().getId());
    }

    @Transactional
    public Expenses getExpensesById(String expensesId){
        return expensesRepository.findById(expensesId)
               .orElseThrow(() -> new IllegalArgumentException("Expenses not found"));
    }

    @Transactional
    public void deleteExpenses(String expensesId){
        expensesRepository.deleteById(expensesId);
    }

    @Transactional
    public Expenses updateExpenses(Expenses expenses){
        Expenses expenses1 = expensesRepository.findById(expenses.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        expenses1.setDescription(expenses.getDescription());
        expenses1.setDate(expenses.getDate());
        return expensesRepository.saveAndFlush(expenses1);
    }


}
