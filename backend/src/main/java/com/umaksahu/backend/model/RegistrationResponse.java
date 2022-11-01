package com.umaksahu.backend.model;

public class RegistrationResponse {
	private boolean registrationSuccess;
	private String description;

	public RegistrationResponse(boolean registrationSuccess, String description) {
		this.registrationSuccess = registrationSuccess;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

	public void setRegistrationSuccess(boolean registrationSuccess) {
		this.registrationSuccess = registrationSuccess;
	}
}
