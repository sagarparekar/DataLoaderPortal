package com.patient.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.patient.exception.PatientDetailsException;
import com.patient.model.PatientDetails;

public interface PatientDetailsService {
	
	public List<PatientDetailsException>  savePatientDetails(MultipartFile excel);
	
	public PatientDetails getPatientByName(String patientName);
	
	public PatientDetails updatePatientDetails(PatientDetails patientDetails);
}
