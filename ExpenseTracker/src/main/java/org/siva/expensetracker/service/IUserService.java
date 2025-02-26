package org.siva.expensetracker.service;

import org.siva.expensetracker.model.User;

public interface IUserService {
	
	User saveUser(User user);
	
	User getUserByEmailAndPassword(String email, String password);
	
	User getUserByEmailAndMobileNumber(String email, String mobileNumber);

}
