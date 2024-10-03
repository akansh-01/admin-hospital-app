package org.jsp.adminHospitalApp.controller;

import java.util.List;

import org.jsp.adminHospitalApp.dto.Admin;
import org.jsp.adminHospitalApp.dto.ResponseStructure;
import org.jsp.adminHospitalApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		
		return adminService.saveAdmin(admin); 
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {
		
		return adminService.updateAdmin(admin);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable(name="id")int id) {
		
		return adminService.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Admin>>> findAll() {
		
		return adminService.findAll();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id")int id) {
		
		return adminService.deleteById(id);
	}
	
	@PostMapping(value = "/verify-phone")
	public ResponseEntity<ResponseStructure<Admin>> verify(@RequestParam(name="phone")long phone,@RequestParam(name = "password") String password) {
		
		return adminService.verify(phone, password);
	}
	
	@PostMapping(value = "/verify-email")
	public ResponseEntity<ResponseStructure<Admin>> verify(@RequestParam(name="email")String email,@RequestParam(name = "password") String password) {
		
		return adminService.verify(email, password);
	}
	
}
