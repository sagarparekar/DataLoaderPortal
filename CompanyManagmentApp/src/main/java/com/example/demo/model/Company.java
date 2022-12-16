package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {

	@Id
	private int companyCode;

	private String companyName;

	private String ceoName;
	
	private String website;
	
	private String stockExchange;

	private double turnover;

	private double currentStockPrice;


	@OneToMany(targetEntity = Stock.class)
	private List<Stock> stockList;

	public int getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", ceoName=" + ceoName
				+ ", website=" + website + ", stockExchange=" + stockExchange + ", turnover=" + turnover
				+ ", currentStockPrice=" + currentStockPrice + ", stockList=" + stockList + "]";
	}

	public Company(int companyCode, String companyName, String ceoName, String website, String stockExchange,
			double turnover, double currentStockPrice, List<Stock> stockList) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.ceoName = ceoName;
		this.website = website;
		this.stockExchange = stockExchange;
		this.turnover = turnover;
		this.currentStockPrice = currentStockPrice;
		this.stockList = stockList;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}
