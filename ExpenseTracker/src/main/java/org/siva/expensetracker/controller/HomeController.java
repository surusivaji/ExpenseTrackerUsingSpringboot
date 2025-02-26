package org.siva.expensetracker.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.siva.expensetracker.model.User;
import org.siva.expensetracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/")
	public String indexPage() {
		return "Index";
	}
	
	@GetMapping("/signup")
	public String registrationPage() {
		return "Register";
	}
	
	@GetMapping("/signin")
	public String loginPage() {
		return "Login";
	}
	
	@PostMapping("/saveUserInformation")
	public String saveUserInformation(HttpSession session, @ModelAttribute User user, @RequestParam("image") MultipartFile multipartFile) {
		try {
			if (!multipartFile.isEmpty()) {
				File file = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(file.getAbsolutePath()+File.separator+"users"+File.separator+multipartFile.getOriginalFilename());
				Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				user.setProfileimage(multipartFile.getOriginalFilename());
			}
			else {
				user.setProfileimage("default.png");
			}
			User saveUser = userService.saveUser(user);
			if (saveUser!=null) {
				session.setAttribute("successMsg", "User Registration Completed");
			}
			else {
				session.setAttribute("failMsg", "something went wrong");
			}
			return "redirect:/signup";
		} catch (Exception e) {
			session.setAttribute("failMsg", "something went wrong");
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/forgetpassword")
	public String forgetPassword() {
		return "ForgetPassword";
	}
	
	@PostMapping("/checkEmailAndMobileNumber")
	public String checkEmailAndMobileNumber(@RequestParam("email") String email, @RequestParam("mobile") String mobile, HttpSession session) {
		User user = userService.getUserByEmailAndMobileNumber(email, mobile);
		if (user!=null) {
			session.setAttribute("changePassword", user);
			return "redirect:/next";
		}
		else {
			session.setAttribute("failMsg", "Invalid credientials");
			return "redirect:/forgetpassword";
		}
	}
	
	@GetMapping("/next")
	public String forgetPassword1(HttpSession session) {
		User user = (User) session.getAttribute("changePassword");
		if (user!=null) {
			return "ForgetPassword1";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@PostMapping("/changePassword")
	public String changePassword(HttpSession session, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
		User user = (User) session.getAttribute("changePassword");
		if (user!=null) {
			if (password.equals(confirmPassword)) {
				user.setPassword(password);
				User saveUser = userService.saveUser(user);
				if (saveUser!=null) {
					session.setAttribute("successMsg", "password changed");
				}
				else {
					session.setAttribute("failMsg", "something went wrong");
				}
				session.removeAttribute("changePassword");
				return "redirect:/signin";
			}
			else {
				session.setAttribute("failMsg", "Passwords most be same");
				return "redirect:/next";
			}
		}
		else {
			return "redirect:/signin";
		}
	}
	

}
