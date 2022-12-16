package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exceptions.CompanyIdAlreadyExistsException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.StockServiceImpl;
import com.example.demo.service.companyServiceImpl;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CompanyController {

	@Autowired
	private companyServiceImpl companyService;

	@Autowired
	private StockServiceImpl stockService;

	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies() {
		List<Company> companyList = companyService.getAllCompanies();

		if (companyList != null) {

			for(Company company : companyList) {
                System.out.println("inside for each:: "+company.toString());
                List<Stock> stockList = stockService.getAllStocks(company.getCompanyCode());
                System.out.println("stockList:: "+stockList);
                company.setStockList(stockList);
            }
		//	return MyResponse.generateCustomResponseFormat("Successfully retrived data!", HttpStatus.OK, companyList);
			return new ResponseEntity<List<Company>>(companyList,HttpStatus.OK);
		}

		//return MyResponse.generateCustomResponseFormat("Could not retrive data!", HttpStatus.CONFLICT, companyList);
		return new ResponseEntity<String>("Could not retrive data!", HttpStatus.CONFLICT);
	}

	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyIdAlreadyExistsException {
		if (!(company.getTurnover() < 10)) {
			if (companyService.addCompany(company) != null) {
				return new ResponseEntity<Company>(company, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Company is empty or turnover is less than 10 Cr", HttpStatus.CONFLICT);

	}

	@DeleteMapping("/deleteCompany/{cCode}")
	public ResponseEntity<?> deleteCompany(@PathVariable("cCode") int cCode) {
		if (stockService.deleteStock(cCode) & companyService.deleteCompany(cCode)) {
			return new ResponseEntity<String>("company record deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("company record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/updateCompanyDetails")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		if (!(company.getTurnover() < 10)) {
			if (companyService.updateCompany(company)) {
				return new ResponseEntity<>(company, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("company reccord is not updated or turnover is less than 10 Cr",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getCompanyByCode/{companyCode}")
	public ResponseEntity<?> getCompanyBycode(@PathVariable("companyCode") int companyCode) {

		Company company = companyService.getCompanyBycode(companyCode);
		if (company != null) {
			return new ResponseEntity<>(company, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Record not available with CompanyCode =" + companyCode, HttpStatus.OK);
	}

}
