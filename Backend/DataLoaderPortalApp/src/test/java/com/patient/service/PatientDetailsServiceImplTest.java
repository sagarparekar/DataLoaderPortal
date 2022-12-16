package com.patient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.patient.exception.PatientDetailsException;
import com.patient.model.PatientDetails;
import com.patient.repository.PatientDetailsRepository;
import com.patient.service.PatientDetailsServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class PatientDetailsServiceImplTest {

	@Mock
	private PatientDetailsRepository repo;

	@InjectMocks
	private PatientDetailsServiceImpl patientDetailsservice;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(patientDetailsservice).build();
	}

	List<PatientDetailsException> patientList = new ArrayList<>();

	@Test
	public void addPatientDetails() throws Exception {
		PatientDetailsException pobj = new PatientDetailsException();// real object

		patientList.add(pobj);

		when(repo.save(any())).thenReturn(pobj);

		assertEquals(1, patientList.size());

	}

	@Test
	public void getPatientByName() throws Exception {
		String pname = "Test Name";
		PatientDetails pobj = new PatientDetails();// real object
		pobj.setPatientAddress("Test Address");
		pobj.setPatientContactNumber("7890654365");
		pobj.setPatientDOB(new Date(07 - 17 - 1996));
		pobj.setPatientDrugId("11111-1111-11");
		pobj.setPatientDrugName("Test Drug");
		pobj.setPatientEmail("test@gmail.com");
		pobj.setPatientName(pname);

		when(repo.findByPatientName(pname)).thenReturn(pobj);

		PatientDetails obj = repo.findByPatientName(pname);

		assertEquals(obj, pobj);
	}

	@Test
	public void updatePatientDetails() throws Exception {
		PatientDetails pobj = new PatientDetails();// real object
		pobj.setPatientAddress("Test Address");
		pobj.setPatientContactNumber("7890654365");
		pobj.setPatientDOB(new Date(07 - 17 - 1996));
		pobj.setPatientEmail("test@gmail.com");
		when(repo.save(any())).thenReturn(null);

		PatientDetails obj = patientDetailsservice.updatePatientDetails(pobj);
		assertEquals(obj, null);
	}

}
