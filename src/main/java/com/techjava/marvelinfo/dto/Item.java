
package com.techjava.marvelinfo.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Item {

    @JsonProperty("name")
    private String mName;
    @JsonProperty("resourceURI")
    private String mResourceURI;
    @JsonProperty("type")
    private String mType;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getResourceURI() {
        return mResourceURI;
    }

    public void setResourceURI(String resourceURI) {
        mResourceURI = resourceURI;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
