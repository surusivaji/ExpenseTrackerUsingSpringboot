package org.siva.expensetracker.repository;

import org.siva.expensetracker.model.Expense;
import org.siva.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	Page<Expense> findByUser(User user, Pageable pageable);

}