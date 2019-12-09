
package com.techjava.stockavailiblity.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

import java.util.Date;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StockInfoDTO {

    @Expose
    private String productId;
    @Expose
    private Date requestTimestamp;
    @Expose
    private Stock stock;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
