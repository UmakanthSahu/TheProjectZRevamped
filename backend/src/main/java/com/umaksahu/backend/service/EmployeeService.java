package com.umaksahu.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.umaksahu.backend.model.request.EmployeeLoginRequest;
import com.umaksahu.backend.model.request.EmployeeRegistrationRequest;
import com.umaksahu.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean isEmployeeCredentialsValid(EmployeeLoginRequest employee) {
		return employeeRepository.findByEmailIdAndPassword(employee.getEmailId(), employee.getPassword()).isPresent();
	}

	public boolean addEmployee(EmployeeRegistrationRequest employee) {
		try {
			employeeRepository.save(employee);			
		}catch(DataIntegrityViolationException e) {
			return false;
		}
		return true;
	}
	
//	private boolean existsById(EmployeeRegistrationRequest employee) {
//		return employeeRepository.existsById(employee.getEmailId());
//	}
}
