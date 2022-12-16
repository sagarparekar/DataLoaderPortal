package com.patient.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.controller.PatientDetailsController;
import com.patient.exception.PatientDetailsException;
import com.patient.model.PatientDetails;
import com.patient.service.PatientDetailsService;

@AutoConfigureMockMvc
@SpringBootTest
public class PatientDetailsControllerTest {

	@Mock
	private PatientDetailsService service;

	@InjectMocks
	private PatientDetailsController controller;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	List<PatientDetailsException> patientList = new ArrayList<>();
	String name = "Test Name";

	@Test
	public void addPatientDetails() throws Exception {
		PatientDetailsException pobj = new PatientDetailsException();// real object

		patientList.add(pobj);
		when(service.savePatientDetails(any())).thenReturn(patientList);

		assertEquals(1, patientList.size());
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/load/patientdata")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(pobj)));

	}

	@Test
	public void getpatientByName() throws Exception {
		String pname = "Test Name";
		PatientDetails pobj = new PatientDetails();// real object
		pobj.setPatientAddress("Test Address");
		pobj.setPatientContactNumber("7890654365");
		pobj.setPatientDOB(new Date(07 - 17 - 1996));
		pobj.setPatientDrugId("11111-1111-11");
		pobj.setPatientDrugName("Test Drug");
		pobj.setPatientEmail("test@gmail.com");
		pobj.setPatientName(pname);

		when(service.getPatientByName(pname)).thenReturn(pobj);

		PatientDetails obj = service.getPatientByName(pname);
		assertEquals(pobj, obj);

		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/load/retrieve/pname")
				.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void updateCompany() throws Exception {
		PatientDetails pobj = new PatientDetails();// real object
		pobj.setPatientAddress("Test Address");
		pobj.setPatientContactNumber("7890654365");
		pobj.setPatientDOB(new Date(07 - 17 - 1996));
		pobj.setPatientEmail("test@gmail.com");

		when(service.updatePatientDetails(pobj)).thenReturn(pobj);

		PatientDetails obj = service.updatePatientDetails(pobj);
		assertEquals(pobj, obj);

		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/load/updatepatient")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(pobj)));

	}

}
