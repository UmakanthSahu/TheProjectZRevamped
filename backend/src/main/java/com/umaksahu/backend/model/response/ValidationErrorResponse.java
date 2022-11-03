package com.umaksahu.backend.model.response;

import java.util.ArrayList;
import java.util.List;

import com.umaksahu.backend.model.Violation;

public class ValidationErrorResponse {
	private List<Violation> violations = new ArrayList<Violation>();

	public ValidationErrorResponse() {
	}

	public ValidationErrorResponse(List<Violation> violations) {
		this.violations = violations;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}
}
