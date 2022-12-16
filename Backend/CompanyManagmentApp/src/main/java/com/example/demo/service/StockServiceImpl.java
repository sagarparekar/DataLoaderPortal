package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockRepository sp;
	
	

	@Override
	public List<Stock> getAllStocks(int companyCode) {

		List<Stock> stockList=sp.getStockList(companyCode);
		return stockList;
	}

	@Override
	public boolean deleteStock(int companyCode) {
		sp.deleteStockData(companyCode);
		return true;
	}

	@Override
	public boolean addStock(Stock stock) {
		
		Stock stockObj=new Stock();
		stockObj.setStockPrice(stock.getStockPrice());
		stockObj.setCompany_code_FK(stock.getCompany_code_FK());
		stockObj.setTimeStamp(stock.getTimeStamp());
		sp.saveAndFlush(stockObj);
		
		return true;
	}

}
