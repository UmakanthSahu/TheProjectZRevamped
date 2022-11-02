package com.umaksahu.backend.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Column(nullable = false)
	private long employeeId;

	@Column(nullable = false, length = 40)
	private String name;

	@Id
	@Column(name = "email_id", length = 30)
	private String emailId;

	@Column(nullable = false, length = 50)
	private char[] password;

	@Column(nullable = false, length = 15)
	private String phoneNumber;

	public Employee() {

	}

	public Employee(long employeeId, String name, String emailId, char[] password, String phoneNumber) {
		this.employeeId = employeeId;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return employeeId;
	}

	public void setId(long id) {
		this.employeeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", emailId=" + emailId + ", password="
				+ Arrays.toString(password) + ", phoneNumber=" + phoneNumber + "]";
	}

}
