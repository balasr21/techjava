package com.techjava.stockavailiblity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techjava.stockavailiblity.domain.Stock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

   public List<Stock> findBystockId(@Param("stockId") String stockId);

   public List<Stock> findByproductId(@Param("productId") String productId);

}
