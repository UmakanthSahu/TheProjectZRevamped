package com.umaksahu.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.umaksahu.backend.model.request.EmployeeLoginRequest;
import com.umaksahu.backend.model.request.EmployeeRegistrationRequest;

@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	private String validEmailId = "admin@admin.com", invalidEmailId = "java@java.com";
	private char[] validPassword = "Admin@123".toCharArray(), invalidPassword = "Java@12456789".toCharArray();

	@BeforeEach
	public void beforeEach() {
		employeeRepository.save(new EmployeeRegistrationRequest(1, "Admin", validEmailId, validPassword, "1234455555"));
	}

	@AfterEach
	void delete() {
		employeeRepository.deleteAll();
	}

	@Test
	void validEmailIdAndPassword() {

		Optional<EmployeeLoginRequest> employeeDetails = employeeRepository.findByEmailIdAndPassword(validEmailId,
				validPassword);

		assertThat(employeeDetails.isPresent()).isTrue();
		assertThat(employeeDetails.get().getEmailId()).isEqualTo(validEmailId);
	}

	@Test
	void validEmailAndInvalidPassword() {
		Optional<EmployeeLoginRequest> employeeDetails = employeeRepository.findByEmailIdAndPassword(validEmailId,
				invalidPassword);

		assertThat(employeeDetails.isPresent()).isFalse();
	}
	

	@Test
	void invalidEmailAndInvalidPassword() {
		Optional<EmployeeLoginRequest> employeeDetails = employeeRepository.findByEmailIdAndPassword(invalidEmailId,
				invalidPassword);

		assertThat(employeeDetails.isPresent()).isFalse();
	}
}
