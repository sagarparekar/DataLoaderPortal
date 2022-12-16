package com.example.demo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mockito;

import com.example.demo.model.Company;

public class CompanyTest {

	@Test
	public void Test01() {

		Company companyObj = Mockito.mock(Company.class);
		when(companyObj.getCompanyName()).thenReturn(null);

		List<String> companynewObj = new ArrayList<String>();

		companynewObj.add("Aaa");

	}

	@Test
	public void Test02() {

		Company companyObj = Mockito.mock(Company.class);
		when(companyObj.getCeoName()).thenReturn(null);

		List<String> companynewObj = new ArrayList<String>();

		companynewObj.add("Marek");

	}

	@Test
	public void Test03() {

		Company companyObj = Mockito.mock(Company.class);
		when(companyObj.getTurnover()).thenReturn(78.8);

		List<Double> companynewObj = new ArrayList<Double>();

		companynewObj.add(78.8);

	}

	
}
