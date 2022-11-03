package com.umaksahu.backend.model.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "employees")
public class EmployeeLoginRequest  implements Persistable<String>{
	@Id
	@Column(name = "email_id", length = 30, nullable = false)
	@NotBlank
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Email Address")
	private String emailId;

	@Column(nullable = false, length = 50)
	private char[] password;

	public EmployeeLoginRequest() {
		super();
	}

	public EmployeeLoginRequest(String emailId, char[] password) {
		this.emailId = emailId.trim();
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public char[] getPassword() {
		return password;
	}

	@Override
	public String getId() {
		return getEmailId();
	}

	@Override
	// making it to always true 
	// throws DataIntegrityViolationException whenever an attempt is made to register with same emailId
	// and will not perform an update 
	public boolean isNew() {
		return true;
	}

}
