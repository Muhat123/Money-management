package com.personal_bank_app.controller;

import com.personal_bank_app.entity.Expenses;
import com.personal_bank_app.repository.ExpensesRepository;
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
    private final ExpensesRepository expensesRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addExpense(@PathVariable String userId, @RequestBody Expenses expenses) {
        try {
            Expenses createdExpense = expensesService.addExpenses(expenses, userId);
            return ResponseEntity.ok(createdExpense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Insufficient Balance");
        }
    }

    @GetMapping("/expenses/{userId}")
    public ResponseEntity<?> getAllExpensesByUserId(@PathVariable String userId) {
        List<Expenses> expenses = expensesService.getAllExpenses(userId);

            if (expenses.isEmpty()) {
                return ResponseEntity.ok("No data to view");
            }

        return ResponseEntity.ok(expenses);
    }

    @GetMapping
    public ResponseEntity<?> getAllExpensesAllUsers(){
        return ResponseEntity.ok(expensesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable String id) {
        try {
            Expenses expenses = expensesService.getExpensesById(id);
            return ResponseEntity.ok(expenses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        try {
            Expenses exp = expensesService.getExpensesById(id);
            if (exp != null) {
                expensesService.deleteExpenses(id);
                return ResponseEntity.ok("Success delete expense");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expenses not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid Request with id " + id);
        }
    }

    @PutMapping
    public ResponseEntity<Expenses> updateExpenses(@RequestBody Expenses expenses){
        Expenses exp = expensesService.updateExpenses(expenses);
        return ResponseEntity.ok(exp);
    }


}
