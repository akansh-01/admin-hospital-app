package org.jsp.adminHospitalApp.repository;

import java.util.Optional;

import org.jsp.adminHospitalApp.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	public Optional<Admin> verify(String email,String password);
	
	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	public Optional<Admin> verify(long phone,String password);
}
