package org.jsp.adminHospitalApp.excecption;

import org.jsp.adminHospitalApp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminHospitalExceptionHandler {

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ResponseStructure<String>> handleAE(AdminException exception){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData("Admin Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatucCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HospitalExceptions.class)
	public ResponseEntity<ResponseStructure<String>> handleHE(HospitalExceptions exception){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData("Hospital Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatucCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
