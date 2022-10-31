package com.umaksahu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umaksahu.backend.model.Employee;
import com.umaksahu.backend.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(path = "/registerEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody @NonNull Employee employee) {
		boolean isEmployeeAdded = employeeService.addEmployee(employee);

		String body = String.format("{\"Registration Status\": \"%s\"}", isEmployeeAdded ? "success" : "failure");

		BodyBuilder bodyBuilder = isEmployeeAdded ? ResponseEntity.ok()
				: ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);

		return bodyBuilder.body(body);
	}

	@PostMapping(path = "/loginEmployee", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody @NonNull Employee employee) {
		boolean isLoginSuccess = employeeService.isEmployeeCredentialsValid(employee);

		String body = String.format("{\"Login Status\": \"%s\"}", isLoginSuccess ? "success" : "failure");

		BodyBuilder bodyBuilder = isLoginSuccess ? ResponseEntity.ok()
				: ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);

		return bodyBuilder.body(body);
	}

}
