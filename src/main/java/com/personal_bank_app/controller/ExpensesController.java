package com.personal_bank_app.controller;

import com.personal_bank_app.entity.Expenses;
import com.personal_bank_app.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addExpense(@PathVariable String userId, @RequestBody Expenses expenses) {
        try {
            Expenses createdExpense = expensesService.addExpenses(expenses, userId);
            return ResponseEntity.ok(createdExpense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Saldo anda tidak mencukupi");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        List<Expenses> expenses = expensesService.getAllExpenses();

        if (expenses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data to view");
        }

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable String id) {
        try {
            Expenses expenses = expensesService.getExpensesById(id);
            return ResponseEntity.ok(expenses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        try {
            Expenses exp = expensesService.getExpensesById(id);
            if (exp != null) {
                expensesService.deleteExpenses(id);
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expenses not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Expenses> updateExpenses(@RequestBody Expenses expenses){
        Expenses exp = expensesService.updateExpenses(expenses);
        return ResponseEntity.ok(exp);
    }


}
