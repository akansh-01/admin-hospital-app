package org.jsp.adminHospitalApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.adminHospitalApp.dao.AdminDao;
import org.jsp.adminHospitalApp.dto.Admin;
import org.jsp.adminHospitalApp.dto.ResponseStructure;
import org.jsp.adminHospitalApp.excecption.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {

		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(adminDao.saveAdmin(admin));
		structure.setMessage("Admin saved");
		structure.setStatucCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {

		Optional<Admin> recAdmin = adminDao.findById(admin.getId());
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			Admin dbAdmin = recAdmin.get();

			dbAdmin.setName(admin.getName());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setPassword(admin.getPassword());

			structure.setData(adminDao.saveAdmin(dbAdmin));
			structure.setMessage("Admin updated");
			structure.setStatucCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AdminException("Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {

		Optional<Admin> recAdmin = adminDao.findById(id);
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}

		throw new AdminException("Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAll(){
		
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		List<Admin> admins = adminDao.findAll();
		
		if(admins.size()>0) {
			
			structure.setData(admins);
			structure.setMessage("Merchants found");
			structure.setStatucCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure,HttpStatus.OK);
		}
		throw new AdminException("No Merchant Available");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id){
		
		Optional<Admin> recAdmin = adminDao.findById(id);
		ResponseStructure<String> structure= new ResponseStructure<>();
		
		if(recAdmin.isPresent()) {
			
			structure.setData("Merchant found");
			structure.setMessage("Merchant deleted");
			structure.setStatucCode(HttpStatus.OK.value());
			
		    adminDao.deleteById(id);
		    
		    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new AdminException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> verify(long phone, String password) {

		Optional<Admin> recAdmin = adminDao.verify(phone, password);
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}

		throw new AdminException("Invalid Admin phone or password");
	}

	public ResponseEntity<ResponseStructure<Admin>> verify(String email, String password) {

		Optional<Admin> recAdmin = adminDao.verify(email, password);
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}

		throw new AdminException("Invalid Admin email or password");
	}

}
