package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CompanyController;
import com.example.demo.model.Company;
import com.example.demo.service.companyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {

	@Mock
	private companyServiceImpl companyService;

	@InjectMocks
	private CompanyController companyController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
	}

	List<Company> companyList = new ArrayList<Company>();

	@Test
	public void getAllCompanySuccess() throws Exception {

		Company company = new Company();

		company.setCompanyCode(101);
		company.setCompanyName("Test Company");
		company.setCeoName("Brian");
		company.setCurrentStockPrice(12.67);
		company.setTurnover(4567);
		company.setWebsite("http://www.test.com/");

		companyList.add(company);
		when(companyService.getAllCompanies()).thenReturn(companyList);

		List<Company> cList = companyService.getAllCompanies();
		assertEquals(companyList, cList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void getAllCompanyFailure() throws Exception {

		companyList.clear();

		when(companyService.getAllCompanies()).thenReturn(companyList);

		assertEquals(0, companyList.size());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void addCompanySuccess() throws Exception {

		Company company = new Company();

		company.setCompanyCode(101);
		company.setCompanyName("Test Company");
		company.setCeoName("Brian");
		company.setCurrentStockPrice(12.67);
		company.setTurnover(4567);
		company.setWebsite("http://www.test.com/");

		companyList.add(company);

		when(companyService.addCompany(any())).thenReturn(company);

		assertEquals(1, companyList.size());

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void addCompanyFailure() throws Exception {

		when(companyService.addCompany(any())).thenReturn(null);

		Company c1 = companyService.addCompany(null);
		assertNull(null);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(c1)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateCompany() throws Exception {
		Company companyObj = new Company();
		companyObj.setCompanyCode(101);
		companyObj.setCompanyName("AA");
		companyObj.setCeoName("Brian");
		companyObj.setTurnover(1233);
		companyObj.setStockExchange("NSE");
		companyObj.setWebsite("http://www.test.com/");

		when(companyService.updateCompany(companyObj)).thenReturn(true);
		boolean c1 = companyService.updateCompany(companyObj);
		assertTrue(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/updateCompany/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(companyObj)));
	}

	@Test
	public void deleteCompany() throws Exception {
		Company companyObj = new Company();
		companyObj.setCompanyCode(101);
		companyObj.setCompanyName("AA");
		companyObj.setCeoName("Brian");
		companyObj.setTurnover(1233);
		companyObj.setStockExchange("NSE");
		companyObj.setWebsite("http://www.test.com/");

		companyList.add(companyObj);
		when(companyService.deleteCompany(101)).thenReturn(true);

		boolean c1 = companyService.deleteCompany(101);
		assertTrue(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/deleteCompany/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(companyObj)));

	}

}
