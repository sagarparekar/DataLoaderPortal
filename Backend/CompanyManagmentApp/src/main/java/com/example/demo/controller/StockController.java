package com.example.demo.controller;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;

import com.example.demo.model.Stock;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/stock")
@CrossOrigin("*")
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	private CompanyService cs2;

	@PostMapping("/addStock/{companyCode}")
	public ResponseEntity<?> addStock(@PathVariable("companyCode") int companyCode, @RequestBody Stock stock) {
		Company existStock = cs2.getCompanyBycode(companyCode);
		if (existStock != null) {
			existStock.setCurrentStockPrice(stock.getStockPrice());
			stock.setStockPrice(stock.getStockPrice());
			stock.setCompany_code_FK(stock.getCompany_code_FK());
			stock.setTimeStamp(stock.getTimeStamp());
			if (cs2.updateCompany(existStock) && stockService.addStock(stock))
				return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Company Stock cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/getAllStocks")
	public ResponseEntity<?> getAllStocks(@RequestParam("companyCode") int companyCode) {

		List<Stock> stockList = stockService.getAllStocks(companyCode);
		if (stockList != null && !stockList.isEmpty()) {

			CacheControl cache = CacheControl.maxAge(30, TimeUnit.MINUTES);
			return new ResponseEntity<Object>(stockList, HttpStatus.OK);

		}
		return new ResponseEntity<String>("Readers not available", HttpStatus.NO_CONTENT);

	}

}
