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

	public EmployeeService() {
	}

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public boolean isEmployeeCredentialsValid(EmployeeLoginRequest employee) {
		try {
			return employeeRepository.findByEmailIdAndPassword(employee.getEmailId(), employee.getPassword())
					.isPresent();
		} finally {
			employee.destroyPassword();
		}
	}

	public boolean addEmployee(EmployeeRegistrationRequest employee) {
		try {
			employeeRepository.save(employee);
		} catch (DataIntegrityViolationException | IllegalArgumentException e) {
			return false;
		} finally {
			if(employee != null)
				employee.destroyPassword();
		}
		return true;
	}

}
