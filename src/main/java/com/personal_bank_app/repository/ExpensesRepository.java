package com.personal_bank_app.repository;

import com.personal_bank_app.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, String> {
    List<Expenses> findAllByUserId(String userId);
}
