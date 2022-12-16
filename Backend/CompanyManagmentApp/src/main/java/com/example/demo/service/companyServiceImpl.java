package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CompanyIdAlreadyExistsException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class companyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public List<Company> getAllCompanies() {

		List<Company> companyList = companyRepo.findAll();
		if (companyList != null && companyList.size() > 0) {
			return companyList;
		}
		return null;
	}

	@Override
	public Company addCompany(Company company) throws CompanyIdAlreadyExistsException {

		Optional<Company> opObj = companyRepo.findById(company.getCompanyCode());
		if (opObj.isPresent()) {
			throw new CompanyIdAlreadyExistsException();
		}
		companyRepo.saveAndFlush(company);
		return company;

		// Without Exception Handling

		/*
		 * if (company != null) { companyRepo.saveAndFlush(company); return company; }
		 * return null;
		 */
	}

	@Override
	public boolean deleteCompany(int companyCode) {
		companyRepo.deleteById(companyCode);
		return true;
	}

	@Override
	public boolean updateCompany(Company company) {

		Company company1 = companyRepo.getById(company.getCompanyCode());
		if (company1 != null) {

			company1.setCompanyName(company.getCompanyName());
			company1.setCeoName(company.getCeoName());
			company1.setWebsite(company.getWebsite());
			company1.setTurnover(company.getTurnover());
			company1.setStockExchange(company.getStockExchange());

			companyRepo.saveAndFlush(company1);

			return true;

		}
		return false;
	}

	@Override
	public Company getCompanyBycode(int companyCode) {
		Optional<Company> company = companyRepo.findById(companyCode);
		if (company.isPresent()) {
			return company.get();
		}
		return null;
	}

}
