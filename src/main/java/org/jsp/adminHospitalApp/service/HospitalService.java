package org.jsp.adminHospitalApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.adminHospitalApp.dao.AdminDao;
import org.jsp.adminHospitalApp.dao.HospitalDao;
import org.jsp.adminHospitalApp.dto.Admin;
import org.jsp.adminHospitalApp.dto.Hospital;
import org.jsp.adminHospitalApp.dto.ResponseStructure;
import org.jsp.adminHospitalApp.excecption.AdminException;
import org.jsp.adminHospitalApp.excecption.HospitalExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Hospital>> save(Hospital hospital, int admin_id) {

		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Hospital> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			Admin admin = recAdmin.get();
			admin.getHospitals().add(hospital);

			hospital.setAdmin(admin);

			structure.setData(hospitalDao.save(hospital));
			structure.setMessage("Hospital Saved");
			structure.setStatucCode(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.CREATED);
		}
		throw new AdminException("Hospital Not Saved as Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<Hospital>> update(Hospital hospital) {

		Optional<Hospital> recHospital = hospitalDao.findById(hospital.getId());

		ResponseStructure<Hospital> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			Hospital dbHospital = recHospital.get();
			dbHospital.setName(hospital.getName());
			dbHospital.setCompany(hospital.getCompany());
			dbHospital.setYoe(hospital.getYoe());

			structure.setMessage("Hospital Updated");
			structure.setStatucCode(HttpStatus.ACCEPTED.value());
			structure.setData(hospitalDao.save(dbHospital));
			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.ACCEPTED);
		}
		throw new HospitalExceptions("Hospital Id Invalid ");
	}

	public ResponseEntity<ResponseStructure<Hospital>> findById(int id) {

		Optional<Hospital> recHospital = hospitalDao.findById(id);
		ResponseStructure<Hospital> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			structure.setData(recHospital.get());
			structure.setMessage("Hospital found");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.OK);
		}

		throw new HospitalExceptions("Invalid hospital id");
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findAll() {

		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalDao.findAll();

		if (hospitals.size() > 0) {

			structure.setData(hospitals);
			structure.setMessage("Hospitals found");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Hospital>>>(structure, HttpStatus.OK);
		}
		throw new HospitalExceptions("No Hospital Available");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		Optional<Hospital> recHospital = hospitalDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			structure.setData("Hospital deleted");
			structure.setMessage("Hospital found");
			structure.setStatucCode(HttpStatus.OK.value());
			hospitalDao.deleteById(id);

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}

		throw new HospitalExceptions("Invalid hospital id");
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(int id) {

		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalDao.findbyAdminId(id);

		if (hospitals.size() > 0) {

			structure.setData(hospitals);
			structure.setMessage("Hospitals details");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Hospital>>>(structure, HttpStatus.OK);
		}
		throw new HospitalExceptions("Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminPhonePassword(long phone,String password) {

		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalDao.findbyAdminPhone(phone, password);

		if (hospitals.size() > 0) {

			structure.setData(hospitals);
			structure.setMessage("Hospitals details");
			structure.setStatucCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Hospital>>>(structure, HttpStatus.OK);
		}
		throw new HospitalExceptions("Invalid Admin Phone or password");
	}

}
