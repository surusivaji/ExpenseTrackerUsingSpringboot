package org.siva.expensetracker.repository;

import org.siva.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmailAndMobilenumber(String email, String mobileNumber);

}
