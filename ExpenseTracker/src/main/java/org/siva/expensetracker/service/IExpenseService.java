package org.siva.expensetracker.service;

import org.siva.expensetracker.model.Expense;
import org.siva.expensetracker.model.User;
import org.springframework.data.domain.Page;

public interface IExpenseService {
	
	Expense saveExpense(Expense expense);
	
	Page<Expense> getAllExpensesByUser(User user, Integer pageNo);
	
	Expense getExpenseById(Integer id);
	
	Boolean deleteExpense(Expense expense);

}
