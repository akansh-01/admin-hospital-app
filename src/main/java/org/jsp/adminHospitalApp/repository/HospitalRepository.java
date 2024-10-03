package org.jsp.adminHospitalApp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.adminHospitalApp.dto.Admin;
import org.jsp.adminHospitalApp.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	@Query("select a.hospitals from Admin a where a.id=?1")
	public List<Hospital> findByAdminId(int id);
	
	@Query("select a.hospitals from Admin a where a.phone=?1 and a.password=?2")
	public List<Hospital> findByPhone(long phone,String password);
}
