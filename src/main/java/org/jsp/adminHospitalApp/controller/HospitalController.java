package org.jsp.adminHospitalApp.controller;

import java.util.List;

import org.jsp.adminHospitalApp.dto.Hospital;
import org.jsp.adminHospitalApp.dto.ResponseStructure;
import org.jsp.adminHospitalApp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/hospitals")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping(value = "/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> save(@RequestBody Hospital hospital,
			@PathVariable(name = "admin_id") int admin_id) {

		return hospitalService.save(hospital, admin_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Hospital>> update(@RequestBody Hospital hospital) {

		return hospitalService.update(hospital);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findById(@PathVariable(name = "id") int id) {

		return hospitalService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {

		return hospitalService.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Hospital>>> findAll() {

		return hospitalService.findAll();
	}

	@PostMapping(value = "/find-adminid")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(@RequestParam(name = "id") int id) {

		return hospitalService.findByAdminId(id);
	}

	@PostMapping(value = "/find-admin-phone")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdmninPhonePassword(
			@RequestParam(name = "phone") long phone, @RequestParam(name = "password") String password) {

		return hospitalService.findByAdminPhonePassword(phone, password);
	}
}
