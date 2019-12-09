package com.techjava.stockavailiblity.service;

import com.google.gson.Gson;
import com.techjava.stockavailiblity.Controller.StockAvailabilityController;
import com.techjava.stockavailiblity.constants.DateFormatterConstants;
import com.techjava.stockavailiblity.constants.ResponseDTOConstants;
import com.techjava.stockavailiblity.domain.Stock;
import com.techjava.stockavailiblity.dto.ResponseDTO;
import com.techjava.stockavailiblity.dto.StockAvailabilityDTO;
import com.techjava.stockavailiblity.dto.StockInfoDTO;
import com.techjava.stockavailiblity.repository.StockRepository;
import com.techjava.stockavailiblity.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService  {

    @Autowired
    StockRepository stockRepository;

    /** The logger. */
    private Logger logger= LoggerFactory.getLogger(StockServiceImpl.class);

    /**
     * getStockInfo - Retrieves stock information available in DB for the given productId
     * @param productId
     * @return
     */

    @Override
    public String getStockInfo(String productId) {

         Gson g=new Gson();
         String requestDate= DateUtil.convertDateToString(new Date(), DateFormatterConstants.YYYY_MM_DD_T_HH_MM_SS_SSS_Z);

         List<Stock> stockInfo=stockRepository.findByproductId(productId);
         List<StockAvailabilityDTO> stockAvailability=new ArrayList<>();
         stockInfo.stream().forEach(stock -> {
             stockAvailability.add(new StockAvailabilityDTO(stock.getStockId(),stock.getProductId(),stock.getQuantity(),requestDate));
         });

         return g.toJson(stockInfo, com.techjava.stockavailiblity.dto.StockAvailabilityDTO.class);


    }

    /**
     * updateStockDetails - This API updates stock details into the DB if the details are valid
     *
     *    Validations being performed by API
     *
     *    ProductId should not be null and existing
     *    StockId should not be null and existing
     *    If requested Timestamp is greater than existing stock timestamp
     *    Quantity should be greater than 0
     *
     * @param stockInfoDTO
     * @return
     */
    @Override
    public ResponseDTO updateStockDetails(StockInfoDTO stockInfoDTO) {

        ResponseDTO responseDTO=new ResponseDTO();

        try{

            if(stockInfoDTO.getProductId()!=null && stockInfoDTO.getStock().getId()!=null && stockInfoDTO.getStock().getQuantity()>=0){

                List<Stock> allStocks=stockRepository.findBystockId(stockInfoDTO.getStock().getId());

                if(!allStocks.isEmpty()){ // Valid stock Check

                    if(validProduct(allStocks,stockInfoDTO.getProductId())){ // Valid Product Check
                        if(isupdatedStockInfo(allStocks,stockInfoDTO.getRequestTimestamp())){ // RequestedTimestamp check
                            allStocks.stream().forEach(stock -> {

                                        stock.setModifiedDate(new Date());
                                        stock.setQuantity(stockInfoDTO.getStock().getQuantity());
                                    });

                            stockRepository.save(allStocks);
                            setResponseDTO(responseDTO, ResponseDTOConstants.SAVED_SUCCESSFULLY,ResponseDTO.Status.SUCCESS,HttpStatus.CREATED);

                        }else{
                            setResponseDTO(responseDTO,ResponseDTOConstants.OUTDATED_STOCK,ResponseDTO.Status.ERROR,HttpStatus.NO_CONTENT);
                        }
                    }else{
                        setResponseDTO(responseDTO,ResponseDTOConstants.UPDATED_FAILED,ResponseDTO.Status.ERROR,HttpStatus.BAD_REQUEST);
                    }

                }else{
                    setResponseDTO(responseDTO,ResponseDTOConstants.UPDATED_FAILED,ResponseDTO.Status.ERROR,HttpStatus.BAD_REQUEST);
                }

            }else{
                setResponseDTO(responseDTO,ResponseDTOConstants.UPDATED_FAILED,ResponseDTO.Status.ERROR,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            logger.error("Error in updating stock info {}",e.getMessage());
        }
        return responseDTO;
    }

    /**
     * setResponseDTO - This method sets response DTO
     *
     * @param responseDTO
     * @param message
     * @param status
     * @param httpStatus
     */
    private void setResponseDTO(ResponseDTO responseDTO, String message, ResponseDTO.Status status, HttpStatus httpStatus) {

        responseDTO.setMessage(message);
        responseDTO.setStatusCode(httpStatus);
        responseDTO.setStatus(status);

    }


    /**
     * isupdatedStockInfo
     *
     * @param allStocks
     * @return
     */
    private boolean isupdatedStockInfo(List<Stock> allStocks,Date requestedTimestamp) {

        return  allStocks
                .stream()
                .filter(stock -> DateUtil.compareDate(stock.getModifiedDate(),requestedTimestamp)<0)
                .findAny().isPresent();

    }

    /**
     * validProduct - This method validate whether given product is valid and existing
     * @param allStocks
     * @return
     */
    private boolean validProduct(List<Stock> allStocks,String productId) {

        return allStocks.stream().filter(stock->stock.getProductId().contains(productId)).findAny().isPresent();

    }


}
