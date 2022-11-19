package com.umaksahu.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umaksahu.backend.model.request.EmployeeLoginRequest;
import com.umaksahu.backend.model.request.EmployeeRegistrationRequest;
import com.umaksahu.backend.model.response.EmployeeLoginResponse;
import com.umaksahu.backend.model.response.EmployeeRegistrationResponse;
import com.umaksahu.backend.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping(path = "/registerEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeRegistrationResponse> registerEmployee(
			@RequestBody @Valid EmployeeRegistrationRequest employee) {

		if (employeeService.addEmployee(employee)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeRegistrationResponse(true, "Success"));
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new EmployeeRegistrationResponse("Email already registered"));
	}

	@PostMapping(path = "/loginEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeLoginResponse> loginEmployee(@RequestBody @Valid EmployeeLoginRequest employee) {

		if (employeeService.isEmployeeCredentialsValid(employee)) {
			return ResponseEntity.ok().body(new EmployeeLoginResponse(true, "Success"));
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new EmployeeLoginResponse("Invalid Credentials"));
	}

}
