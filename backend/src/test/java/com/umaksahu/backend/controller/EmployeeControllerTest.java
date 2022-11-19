package com.umaksahu.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umaksahu.backend.model.request.EmployeeLoginRequest;
import com.umaksahu.backend.model.request.EmployeeRegistrationRequest;
import com.umaksahu.backend.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private EmployeeLoginRequest employeeLoginDetails = new EmployeeLoginRequest("test@test.com", "Test@123456".toCharArray());
	private EmployeeRegistrationRequest employeeRegistrationDetails = new EmployeeRegistrationRequest(employeeLoginDetails, 1,
			"Test Name", "1234567897");

	@Test
	void registerNewEmployeeSuccess() throws Exception {
		when(employeeService.addEmployee(any(EmployeeRegistrationRequest.class))).thenReturn(true);

		String employeeRegistrationDetailsJson = objectMapper.writeValueAsString(employeeRegistrationDetails);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/registerEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(employeeRegistrationDetailsJson)).andExpect(MockMvcResultMatchers.status().isCreated());

		verify(employeeService).addEmployee(any(EmployeeRegistrationRequest.class));
	}

	@Test
	void registerDuplicateEmployeeFailure() throws Exception {
		when(employeeService.addEmployee(any(EmployeeRegistrationRequest.class))).thenReturn(false);

		String employeeRegistrationDetailsJson = objectMapper.writeValueAsString(employeeRegistrationDetails);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/registerEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(employeeRegistrationDetailsJson))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

		verify(employeeService).addEmployee(any(EmployeeRegistrationRequest.class));
	}

	@Test
	void loginEmployeeWithCorrectUserNameAndPassword() throws Exception {
		when(employeeService.isEmployeeCredentialsValid(any(EmployeeLoginRequest.class))).thenReturn(true);

		String employeeLoginDetailsJson = objectMapper.writeValueAsString(employeeLoginDetails);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/loginEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(employeeLoginDetailsJson))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(employeeService).isEmployeeCredentialsValid(any(EmployeeLoginRequest.class));
	}
	
	@Test
	void loginEmployeeWithInvalidUserNameAndPassword() throws Exception {
		when(employeeService.isEmployeeCredentialsValid(any(EmployeeLoginRequest.class))).thenReturn(false);

		String employeeLoginDetailsJson = objectMapper.writeValueAsString(employeeLoginDetails);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/loginEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(employeeLoginDetailsJson))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());

		verify(employeeService).isEmployeeCredentialsValid(any(EmployeeLoginRequest.class));
	}

	@Test
	void loginEmployeeWithInvalidFormat() throws Exception {
		String employeeLoginDetailsJson = objectMapper.writeValueAsString(new EmployeeLoginRequest("test@test", "test@12".toCharArray()));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/loginEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(employeeLoginDetailsJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

		verify(employeeService, never()).isEmployeeCredentialsValid(any(EmployeeLoginRequest.class));
	}
}
