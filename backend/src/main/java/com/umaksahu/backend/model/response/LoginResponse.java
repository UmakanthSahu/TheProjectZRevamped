package com.umaksahu.backend.model.response;

public class LoginResponse extends Response {
	private final boolean loginSuccess;

	public String getDescription() {
		return description;
	}

	public LoginResponse(boolean loginSuccess, String description) {
		super(description);
		this.loginSuccess = loginSuccess;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

}
