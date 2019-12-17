
package com.techjava.marvelinfo.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Url {

    @JsonProperty("type")
    private String mType;
    @JsonProperty("url")
    private String mUrl;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
