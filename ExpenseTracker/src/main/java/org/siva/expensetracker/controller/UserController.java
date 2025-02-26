package org.siva.expensetracker.controller;

import org.siva.expensetracker.model.Expense;
import org.siva.expensetracker.model.User;
import org.siva.expensetracker.service.IExpenseService;
import org.siva.expensetracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IExpenseService expenseService;
	
	@ModelAttribute
	public void commonData(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			model.addAttribute("user", user);
		}
	}
	
	@PostMapping("/getUserInformation")
	private String getUserInformation(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userService.getUserByEmailAndPassword(email, password);
		if (user!=null) {
			session.setAttribute("user", user);
			return "redirect:/user/home";
		}
		else {
			session.setAttribute("failMsg", "Invalid credientials");
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/home")
	public String homePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			return "User/Home";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	
	@GetMapping("/user/addExpense")
	public String addExpensePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			return "User/AddExpense";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@PostMapping("/user/saveExpense")
	public String saveExpense(@ModelAttribute Expense expense, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			expense.setUser(user);
			Expense saveExpense = expenseService.saveExpense(expense);
			if (saveExpense!=null) {
				session.setAttribute("successMsg", "Your Expense Saved");
			}
			else {
				session.setAttribute("failMsg", "Something went wrong");
			}
			return "redirect:/user/addExpense";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/viewExpense")
	public String viewExpensePage(HttpSession session, Model model, @RequestParam(defaultValue = "0") Integer pageNo) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			Page<Expense> page = expenseService.getAllExpensesByUser(user, pageNo);
			if (page!=null) {
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("expenses", page.getContent());
				return "User/ViewExpense";
			}
			else {
				return "redirect:/user/home";
			}
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/editExpense/{id}")
	public String editExpensePage(HttpSession session, Model model, @PathVariable("id") Integer id) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			Expense expense = expenseService.getExpenseById(id);
			if (expense!=null) {
				model.addAttribute("expense", expense);
				return "User/EditExpense";
			}
			else {
				session.setAttribute("failMsg", "expense not found");
				return "redirect:/user/viewExpense";
			}
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@PostMapping("/user/updateExpense")
	public String updateExpenseInformation(HttpSession session, @ModelAttribute Expense expense) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			expense.setUser(user);
			Expense saveExpense = expenseService.saveExpense(expense);
			if (saveExpense!=null) {
				session.setAttribute("successMsg", "Expense updated");
			}
			else {
				session.setAttribute("failMsg", "Something went wrong");
			}
			return "redirect:/user/viewExpense";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/deleteExpense/{id}")
	public String deleteExpense(HttpSession session, @PathVariable("id") Integer Id) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			Expense expense = expenseService.getExpenseById(Id);
			if (expense!=null) {
				Boolean deleteExpense = expenseService.deleteExpense(expense);
				if (deleteExpense) {
					session.setAttribute("successMsg", "Expense deleted");
				}
				else {
					session.setAttribute("failMsg", "Something went wrong");
				}
			}
			else {
				session.setAttribute("failMsg", "Expense not found");
			}
			return "redirect:/user/viewExpense";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/profile")
	public String profilePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			return "User/MyProfile";
		}
		else {
			return "redirect:/signin";
		}
	}
	
	@GetMapping("/user/logout")
	public String logoutPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user!=null) {
			session.removeAttribute("user");
			session.setAttribute("logoutInfo", "Logout");
			return "redirect:/signin";
		}
		else {
			return "redirect:/signin";
		}
	}

}
