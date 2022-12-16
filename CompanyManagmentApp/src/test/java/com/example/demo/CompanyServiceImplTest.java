package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.Company;

import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.companyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceImplTest {

	@Mock
	private CompanyRepository companyRepo;

	@InjectMocks
	private companyServiceImpl companyService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(companyService).build();
	}

	List<Company> companyList = new ArrayList<Company>();

	@Test
	public void getAllCompanyData() throws Exception {
		Company companyObj = new Company();
		companyObj.setCompanyCode(101);
		companyObj.setCompanyName("Test Company");
		companyObj.setCeoName("Brian");
		companyObj.setTurnover(1233);
		companyObj.setStockExchange("NSE");
		companyObj.setWebsite("http://www.test.com/");
		companyObj.setCurrentStockPrice(34.88);

		companyList.add(companyObj);

		when(companyRepo.findAll()).thenReturn(companyList);

		List<Company> cList = companyService.getAllCompanies();
		assertEquals(companyList, cList);
	}

	@Test
	public void getAllCompanyFailure() throws Exception {

		when(companyRepo.findAll()).thenReturn(null);

		List<Company> cList = companyService.getAllCompanies();
		assertNull(cList);

	}

	@Test
	public void addCompanySuccess() throws Exception {
		Company companyObj = new Company();
		companyObj.setCompanyName("AA");
		companyObj.setCeoName("Brian");
		companyObj.setTurnover(1233);
		companyObj.setStockExchange("NSE");
		companyObj.setWebsite("http://www.test.com/");
		companyObj.setCurrentStockPrice(34.88);

		companyList.add(companyObj);
		when(companyRepo.save(any())).thenReturn(companyObj);
		Company c1 = companyService.addCompany(companyObj);
		assertEquals(companyObj, c1);

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

		when(companyRepo.save(any())).thenReturn(true);
		boolean c1 = companyService.updateCompany(companyObj);
		assertFalse(c1);
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

		boolean c1 = companyService.deleteCompany(101);

	}

}
