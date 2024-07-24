package com.personal_bank_app.service;

import com.personal_bank_app.entity.Expenses;

import java.util.List;

public interface ExpensesService {
    Expenses addExpenses(Expenses expenses, String userId);
    List<Expenses> getAllExpenses(String id);
    Expenses getExpensesById(String expensesId);
    void deleteExpenses(String expensesId);
    Expenses updateExpenses(Expenses expenses);
}
