
package com.techjava.marvelinfo.dto;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Events {

    @JsonProperty("available")
    private Long mAvailable;
    @JsonProperty("collectionURI")
    private String mCollectionURI;
    @JsonProperty("items")
    private List<Item> mItems;
    @JsonProperty("returned")
    private Long mReturned;

    public Long getAvailable() {
        return mAvailable;
    }

    public void setAvailable(Long available) {
        mAvailable = available;
    }

    public String getCollectionURI() {
        return mCollectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        mCollectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public Long getReturned() {
        return mReturned;
    }

    public void setReturned(Long returned) {
        mReturned = returned;
    }

}
