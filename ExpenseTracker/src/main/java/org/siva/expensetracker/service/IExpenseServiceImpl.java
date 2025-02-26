package org.siva.expensetracker.service;

import java.util.Optional;

import org.siva.expensetracker.model.Expense;
import org.siva.expensetracker.model.User;
import org.siva.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IExpenseServiceImpl implements IExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Expense saveExpense(Expense expense) {
		try {
			Expense save = expenseRepository.save(expense);
			return save;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Page<Expense> getAllExpensesByUser(User user, Integer pageNo) {
		try {
			Pageable pageable = PageRequest.of(pageNo, 8);
			Page<Expense> page = expenseRepository.findByUser(user, pageable);
			return page;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Expense getExpenseById(Integer id) {
		try {
			Optional<Expense> optional = expenseRepository.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean deleteExpense(Expense expense) {
		try {
			expenseRepository.delete(expense);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
