package org.siva.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userid;
	@Column(name="FullName", length=50, nullable = false)
	private String fullname;
	@Column(name="Email", length=50, nullable = false, unique = true)
	private String email;
	@Column(name="Password", length = 50, nullable = false)
	private String password;
	@Column(name="MobileNumber", length=10, nullable = false)
	private String mobilenumber;
	@Column(name="ProfileImage", nullable=false)
	private String profileimage;
	
}
