package com.umaksahu.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umaksahu.backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT emailId FROM Employee WHERE email_id=?1 AND password=?2")
	public Optional<Employee> isValidCredentials(String emailId, char[] password);
	
	public Optional<Employee> findByEmailId(String emailId);
}
