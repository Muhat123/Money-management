package com.personal_bank_app.service;

import com.personal_bank_app.entity.Expenses;

import java.util.List;

public interface ExpensesService {
    Expenses addExpenses(Expenses expenses, String userId);
    List<Expenses> getAllExpenses();
    Expenses getExpensesById(String expensesId);
    void deleteExpenses(String expensesId);
    Expenses updateExpenses(Expenses expenses);
}
