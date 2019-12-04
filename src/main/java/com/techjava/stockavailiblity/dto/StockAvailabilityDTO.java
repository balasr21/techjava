
package com.techjava.stockavailiblity.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StockAvailabilityDTO {

    @SerializedName("id")
    private String mId;
    @SerializedName("productId")
    private String mProductId;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("timestamp")
    private String mTimestamp;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

}
