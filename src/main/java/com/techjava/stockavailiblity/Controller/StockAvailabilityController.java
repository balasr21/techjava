package com.techjava.stockavailiblity.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/stock/")
public class StockAvailabilityController {

    /** The logger. */
    private Logger logger= LoggerFactory.getLogger(StockAvailabilityController.class);



}
