package com.umaksahu.backend.model.response;

public class RegistrationResponse extends Response{
	private final boolean registrationSuccess;
	
	
	public RegistrationResponse(boolean registrationSuccess, String description) {
		super(description);
		this.registrationSuccess = registrationSuccess;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

}
