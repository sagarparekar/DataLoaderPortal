package com.patient.model;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.patient.model.PatientDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PatientDetailsTest {

	@Test
	public void test01() {

		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientName("Sagar Parekar");
		when(pObj.getPatientName()).thenReturn(real.getPatientName());
		assertEquals(real.getPatientName(), pObj.getPatientName());

	}

	@Test
	public void test02() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientAddress("Madhuban society, lakshminagar,pune,Maharashtra");
		when(pObj.getPatientAddress()).thenReturn(real.getPatientAddress());
		assertEquals(real.getPatientAddress(), pObj.getPatientAddress());
	}

	@Test
	public void test03() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientContactNumber("9977883322");
		when(pObj.getPatientContactNumber()).thenReturn(real.getPatientContactNumber());
		assertEquals(real.getPatientContactNumber(), pObj.getPatientContactNumber());
	}

	@Test
	public void test04() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		Date d1 = new Date();
		PatientDetails real = new PatientDetails();
		real.setPatientDOB(d1);
		when(pObj.getPatientDOB()).thenReturn(real.getPatientDOB());
		assertEquals(real.getPatientDOB(), pObj.getPatientDOB());
	}

	@Test
	public void test05() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientDrugId("55555-1111-11");
		when(pObj.getPatientDrugId()).thenReturn(real.getPatientDrugId());
		assertEquals(real.getPatientDrugId(), pObj.getPatientDrugId());
	}

	@Test
	public void test07() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientEmail("sagar@gmail.com");
		when(pObj.getPatientEmail()).thenReturn(real.getPatientEmail());
		assertEquals(real.getPatientEmail(), pObj.getPatientEmail());
	}

	@Test
	public void test06() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setPatientDrugName("TTYY");
		when(pObj.getPatientDrugName()).thenReturn(real.getPatientDrugName());
		assertEquals(real.getPatientDrugName(), pObj.getPatientDrugName());
	}

	@Test
	public void test08() {
		PatientDetails pObj = Mockito.mock(PatientDetails.class);// creating mock object

		PatientDetails real = new PatientDetails();
		real.setStatus("Inducted");
		when(pObj.getStatus()).thenReturn(real.getStatus());
		assertEquals(real.getStatus(), pObj.getStatus());
	}

}
