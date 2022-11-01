package com.umaksahu.backend.model;

public class LoginResponse {
	private boolean isValidEmail;
	private String description;
	private boolean loginSuccess;

	public boolean isValidEmail() {
		return isValidEmail;
	}

	public void setValidEmail(boolean isValidEmail) {
		this.isValidEmail = isValidEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public LoginResponse(boolean isValidEmail, String description, boolean loginSuccess) {
		this.isValidEmail = isValidEmail;
		this.description = description;
		this.loginSuccess = loginSuccess;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	

}
