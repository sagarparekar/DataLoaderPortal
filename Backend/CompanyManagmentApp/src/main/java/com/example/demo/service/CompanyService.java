package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.CompanyIdAlreadyExistsException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;

public interface CompanyService {
	
	public List<Company> getAllCompanies();
	
	public Company addCompany(Company company) throws CompanyIdAlreadyExistsException;
	
	public boolean deleteCompany(int cCode);
	
	public boolean updateCompany(Company company);
	
	public Company getCompanyBycode(int cCode);
}
