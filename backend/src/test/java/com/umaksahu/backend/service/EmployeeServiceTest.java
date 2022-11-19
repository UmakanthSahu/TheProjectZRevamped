package com.umaksahu.backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.umaksahu.backend.model.request.EmployeeLoginRequest;
import com.umaksahu.backend.model.request.EmployeeRegistrationRequest;
import com.umaksahu.backend.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	private EmployeeService employeeService;

	private ArgumentCaptor<EmployeeRegistrationRequest> employeeArgumentCaptor = ArgumentCaptor
			.forClass(EmployeeRegistrationRequest.class);

	private EmployeeRegistrationRequest employeeToBeRegistered = null;

	private EmployeeLoginRequest employeeLoginDetails = new EmployeeLoginRequest("test@gmail.com",
			"Test@12345".toCharArray());

	@Mock
	private EmployeeRepository employeeRepository;

	@BeforeEach
	public void init() {
		employeeService = new EmployeeService(employeeRepository);
		employeeToBeRegistered = new EmployeeRegistrationRequest(1, "Test", "test@gmail.com",
				"Test@12345".toCharArray(), "1245678909");
	}

	@Test
	public void addEmployeeSuccessful() {

		// when
		employeeService.addEmployee(employeeToBeRegistered);

		// then
		verify(employeeRepository).save(employeeArgumentCaptor.capture());

		EmployeeRegistrationRequest capturedEmployee = employeeArgumentCaptor.getValue();

		// assert
		assertThat(capturedEmployee).isEqualTo(employeeToBeRegistered);
		verifyPasswordIsDestroyed(employeeToBeRegistered.getPassword());
	}

	@Test
	public void addDuplicateEmployee() {

		when(employeeRepository.save(employeeToBeRegistered)).thenThrow(DataIntegrityViolationException.class);
		assertThat(employeeService.addEmployee(employeeToBeRegistered)).isFalse();
		verifyPasswordIsDestroyed(employeeToBeRegistered.getPassword());

	}

	@Test
	public void addNullEmployee() {
		when(employeeRepository.save(null)).thenThrow(IllegalArgumentException.class);
		assertThat(employeeService.addEmployee(null)).isFalse();
	}

	@Test
	public void loginWithValidCredentials() {
		when(employeeRepository.findByEmailIdAndPassword(anyString(), any()))
				.thenReturn(Optional.of(employeeLoginDetails));
		
		assertThat(employeeService.isEmployeeCredentialsValid(employeeLoginDetails)).isTrue();
		verifyPasswordIsDestroyed(employeeLoginDetails.getPassword());

	}

	@Test
	public void loginWithInValidCredentials() {
		when(employeeRepository.findByEmailIdAndPassword(anyString(), any())).thenReturn(Optional.empty());
		assertThat(employeeService.isEmployeeCredentialsValid(employeeLoginDetails)).isFalse();
		verifyPasswordIsDestroyed(employeeLoginDetails.getPassword());

	}

	private void verifyPasswordIsDestroyed(char[] password) {
		assertThat(password).containsOnly('*');
	}
}
