package org.siva.expensetracker.service;

import org.siva.expensetracker.model.User;
import org.siva.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Service
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		try {
			User save = userRepository.save(user);
			return save;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		try {
			User user = userRepository.findByEmailAndPassword(email, password);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public User getUserByEmailAndMobileNumber(String email, String mobileNumber) {
		try {
			User user = userRepository.findByEmailAndMobilenumber(email, mobileNumber);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("successMsg");
		session.removeAttribute("failMsg");
		session.removeAttribute("logoutInfo");
	}

}
