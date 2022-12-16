package com.patient.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.patient.model.PatientDetails;
import com.patient.repository.PatientDetailsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PatientDetailsRepositoryTest {

	@Autowired
	PatientDetailsRepository patientRepo;

	@Test
	public void getPatientByNameTest() {

		PatientDetails patient = patientRepo.findByPatientName("Sagar D");

		assertNotNull(patient);

	}
}
