package com.techjava.stockavailiblity.service;


import com.techjava.stockavailiblity.dto.ResponseDTO;
import com.techjava.stockavailiblity.dto.StockInfoDTO;

public interface StockService {

    /**
     * getStockInfo - Retrieves stock information available in DB for the given productId
     * @param productId
     * @return
     */

    public String getStockInfo(String productId);


    ResponseDTO updateStockDetails(StockInfoDTO stockInfoDTO);

}
