package com.umaksahu.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umaksahu.backend.model.request.EmployeeLoginRequest;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeLoginRequest, String> {

//	@Query("SELECT emailId FROM Employee WHERE email_id=?1 AND password=?2")
	public Optional<EmployeeLoginRequest> findByEmailIdAndPassword(String emailId, char[] password);

}
