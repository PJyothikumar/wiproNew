package com.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

@Entity
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "first_name is mandatory")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "last_name is mandatory")
	@Column(name = "last_name")
	private String lastName;

	@Email(message = "Email should be valid")
	@NotEmpty(message = "email is mandatory")
	@Column(name = "email")
	private String email;

	@NotEmpty(message = "password is mandatory")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "phone is mandatory")
	@Column(name = "phone")
	private String phone;

	@Past(message = "Date of Birth cannot be in the future")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @Column(name = "dateOfBirth", nullable = false)
	private LocalDate dateOfBirth;

	public Integer getId() {
		return id;
	}

	public Customers() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public Customers(String firstName, String lastName, String email, String password, String phone,
			LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
