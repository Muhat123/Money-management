package com.personal_bank_app.repository;

import com.personal_bank_app.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, String> {
}
