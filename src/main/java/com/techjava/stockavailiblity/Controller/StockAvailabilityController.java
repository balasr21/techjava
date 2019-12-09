package com.techjava.stockavailiblity.Controller;

import com.techjava.stockavailiblity.dto.ResponseDTO;
import com.techjava.stockavailiblity.dto.StockInfoDTO;
import com.techjava.stockavailiblity.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;


@RestController
@CrossOrigin
@RequestMapping("/stock/")
public class StockAvailabilityController {

    @Autowired
    StockService stockService;

    /** The logger. */
    private Logger logger= LoggerFactory.getLogger(StockAvailabilityController.class);


    @CrossOrigin
    @GetMapping("/")
    public String getStockInfo(@QueryParam("productId") String productId){

        return stockService.getStockInfo(productId);

    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateStockDetails(@RequestBody final StockInfoDTO stockInfoDTO){

        return ResponseEntity.ok(stockService.updateStockDetails(stockInfoDTO));

    }


}
