package com.umaksahu.backend.model.response;

public class EmployeeRegistrationResponse extends EmployeeResponse {
	private final boolean registrationSuccess;

	public EmployeeRegistrationResponse(String description) {
		super(description);
		registrationSuccess = false;
	}

	public EmployeeRegistrationResponse(boolean registrationSuccess, String description) {
		super(description);
		this.registrationSuccess = registrationSuccess;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

}
