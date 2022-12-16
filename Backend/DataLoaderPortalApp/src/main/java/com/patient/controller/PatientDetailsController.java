package com.patient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.patient.exception.PatientDetailsException;
import com.patient.model.PatientDetails;
import com.patient.service.PatientDetailsService;

@RestController
@CrossOrigin("*")
public class PatientDetailsController {

	@Autowired
	PatientDetailsService patientDetailsService;

	@PostMapping("/load/patientdata")
	@RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
	public List<PatientDetailsException> uploadPatientDetails(@RequestParam("file") MultipartFile excel)
			throws IOException {
		List<PatientDetailsException> detailsExceptions = new ArrayList<>();
		if (!excel.isEmpty()) {
			detailsExceptions = patientDetailsService.savePatientDetails(excel);
			if (detailsExceptions.isEmpty()) {
				PatientDetailsException exceptio = new PatientDetailsException();
				exceptio.setExceptionMessage("File Uploaded successfully");
				detailsExceptions.add(exceptio);
			}
		} else {
			PatientDetailsException exceptio = new PatientDetailsException();
			exceptio.setExceptionMessage("Please Upload Excel file");
			detailsExceptions.add(exceptio);
		}
		return detailsExceptions;
	}
	
	@GetMapping("/load/retrieve/{patientName}")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<?> getpatientByName(@PathVariable String patientName) {
		PatientDetails patientDetails=patientDetailsService.getPatientByName(patientName);
		if(patientDetails != null) {
			return new ResponseEntity<>(patientDetails, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Record not available with Patient Name = "+patientName, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/load/updatepatient")
	@RolesAllowed("ROLE_ADMIN")
	public  ResponseEntity<?> updatePatientDetails(@RequestBody PatientDetails patientDetails){
		//return new ResponseEntity<>(patientDetailsService.updatePatientDetails(patientDetails), HttpStatus.OK);
		if(null!=patientDetailsService.updatePatientDetails(patientDetails)) {
			return new ResponseEntity<>(patientDetailsService.updatePatientDetails(patientDetails), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Patient Details not updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
