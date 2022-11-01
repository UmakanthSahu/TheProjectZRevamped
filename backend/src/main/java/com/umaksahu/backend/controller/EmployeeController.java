package com.umaksahu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umaksahu.backend.model.Employee;
import com.umaksahu.backend.model.LoginResponse;
import com.umaksahu.backend.model.RegistrationResponse;
import com.umaksahu.backend.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(path = "/registerEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegistrationResponse> addEmployee(@RequestBody @NonNull Employee employee) {
		boolean isEmployeeAdded = employeeService.addEmployee(employee);
		return ResponseEntity.status(isEmployeeAdded ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY).body(
				new RegistrationResponse(isEmployeeAdded, isEmployeeAdded ? "Success" : "Email already registered"));
	}

	@PostMapping(path = "/loginEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody @NonNull Employee employee) {
		boolean isEmailRegistered = employeeService.isEmployeeAlreadyRegistered(employee);
		if (!isEmailRegistered) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new LoginResponse(isEmailRegistered, "Email not registered", false));
		} else {
			boolean isLoginSuccess = employeeService.isEmployeeCredentialsValid(employee);
			return ResponseEntity.status(isLoginSuccess ? HttpStatus.OK : HttpStatus.UNAUTHORIZED)
					.body(new LoginResponse(isEmailRegistered, isLoginSuccess ? "Success" : "Wrong Password",
							isLoginSuccess));
		}
	}

}
