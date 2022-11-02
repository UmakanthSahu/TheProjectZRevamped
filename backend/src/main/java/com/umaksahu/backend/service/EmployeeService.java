package com.umaksahu.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umaksahu.backend.model.Employee;
import com.umaksahu.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public boolean isEmployeeCredentialsValid(Employee employee) {
		return employeeRepository.findByEmailIdAndPassword(employee.getEmailId(), employee.getPassword()).isPresent();
	}

	public boolean addEmployee(Employee employee) {
		if(existsById(employee)) {
			return false;
		}
		
		employeeRepository.save(employee);
		return true;
	}
	
	private boolean existsById(Employee employee) {
		return employeeRepository.existsById(employee.getEmailId());
	}
}
