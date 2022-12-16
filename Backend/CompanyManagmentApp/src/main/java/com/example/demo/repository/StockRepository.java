package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Integer>
{
	@Query(value="select s from Stock s where s.company_code_FK= :companyCode")
	public List<Stock> getStockList(@Param("companyCode") int companyCode);
	
	@Modifying
	@Query(value="delete from Stock where company_code_FK= :companyCode")
	public void deleteStockData(@Param("companyCode") int companyCode);


}
