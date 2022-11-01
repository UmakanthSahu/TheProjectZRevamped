package com.umaksahu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umaksahu.backend.model.Employee;
import com.umaksahu.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public boolean isEmployeeCredentialsValid(Employee employee) {
		return employeeRepository.isValidCredentials(employee.getEmailId(), employee.getPassword()).isPresent();

	}

	public boolean addEmployee(Employee employee) {
		if (isEmployeeAlreadyRegistered(employee))
			return false;
		return employeeRepository.save(employee) != null;
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public boolean isEmployeeAlreadyRegistered(Employee employee) {
		return employeeRepository.findByEmailId(employee.getEmailId()).isPresent();
	}
}
