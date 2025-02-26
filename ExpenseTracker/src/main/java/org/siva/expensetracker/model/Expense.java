package org.siva.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ExpenseId")
	private int id;
	@Column(name="Title", length = 45, nullable = false)
	private String title;
	@Column(name="Date", nullable = false)
	private String date;
	@Column(name="Time", nullable = false)
	private String time;
	@Column(name="Description", nullable = false, length = 100)
	private String description;
	@Column(name="Price", nullable = false)
	private String price;
	@ManyToOne
	private User user;

}
