package com.patient.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.patient.model.AuthRequest;

public class AuthRequestTest {

	@Test
	public void test01() {
		AuthRequest authObj = Mockito.mock(AuthRequest.class);// creating mock object

		AuthRequest real = new AuthRequest();
		real.setUsername("sagartest@gmail.com");

		when(authObj.getUsername()).thenReturn(real.getUsername());
		assertEquals(real.getUsername(), authObj.getUsername());
	}

	@Test
	public void test02() {
		AuthRequest authObj = Mockito.mock(AuthRequest.class);// creating mock object

		AuthRequest real = new AuthRequest();
		real.setPassword("sagar@123");
		when(authObj.getPassword()).thenReturn(real.getPassword());
		assertEquals(real.getPassword(), authObj.getPassword());
	}

}
