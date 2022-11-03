package com.umaksahu.backend.model.response;

public class EmployeeLoginResponse extends EmployeeResponse {
	private final boolean loginSuccess;

	public EmployeeLoginResponse(String description) {
		super(description);
		loginSuccess = false;
	}

	public EmployeeLoginResponse(boolean loginSuccess, String description) {
		super(description);
		this.loginSuccess = loginSuccess;
	}

	public String getDescription() {
		return description;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

}
