package com.umaksahu.backend.model.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "employees")
public class EmployeeRegistrationRequest extends EmployeeLoginRequest {

	@Column(name = "employee_id", nullable = false)
	private long employeeId;

	public EmployeeRegistrationRequest() {
	}

	@Column(nullable = false, length = 40)
	@NotBlank
	@Pattern(regexp = "^[A-Za-z\\s]{2,40}$", message = "Invalid Name")
	private String name;

	@Column(name = "phone_number", nullable = false, length = 15)
	@Pattern(regexp = "^[0-9+-]{8,15}", message = "Phone number must not be less than 8 or greater than 16 digits")
	private String phoneNumber;

	public EmployeeRegistrationRequest(long employeeId, String name, String emailId, char[] password,
			String phoneNumber) {
		super(emailId, password);
		this.employeeId = employeeId;
		this.name = name.trim();
		this.phoneNumber = phoneNumber.trim();
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
