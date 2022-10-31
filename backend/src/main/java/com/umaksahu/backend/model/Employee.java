package com.umaksahu.backend.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	private long employeeId;
	private String name;
	@Column(name = "email_id")
	private String emailId;
	private char[] password;
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
