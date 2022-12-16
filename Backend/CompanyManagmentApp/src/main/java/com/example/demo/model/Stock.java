package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Stock {

	@Id
	@GeneratedValue
	private int transactionId;

	private int company_code_FK;

	double stockPrice;

	Date timeStamp;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCompany_code_FK() {
		return company_code_FK;
	}

	public void setCompany_code_FK(int company_code_FK) {
		this.company_code_FK = company_code_FK;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@PrePersist
	public void setTimeStamp() {
		if (this.timeStamp == null) {
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		}
	}

}
