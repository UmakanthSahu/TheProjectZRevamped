package com.umaksahu.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umaksahu.backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	@Query("SELECT emailId FROM Employee WHERE email_id=?1 AND password=?2")
	public Optional<Employee> findByEmailIdAndPassword(String emailId, char[] password);

}
