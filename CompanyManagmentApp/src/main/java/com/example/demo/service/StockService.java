package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Stock;

public interface StockService {
	
	public List<Stock> getAllStocks(int companyCode);
	
	public boolean deleteStock(int companyCode);
	
	public boolean addStock(Stock stock);

}
