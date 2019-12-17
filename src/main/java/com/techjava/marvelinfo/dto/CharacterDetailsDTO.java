
package com.techjava.marvelinfo.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CharacterDetailsDTO {

    @JsonProperty("attributionHTML")
    private String mAttributionHTML;
    @JsonProperty("attributionText")
    private String mAttributionText;
    @JsonProperty("code")
    private Long mCode;
    @JsonProperty("copyright")
    private String mCopyright;
    @JsonProperty("data")
    private Data mData;
    @JsonProperty("etag")
    private String mEtag;
    @JsonProperty("status")
    private String mStatus;

    public String getAttributionHTML() {
        return mAttributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        mAttributionHTML = attributionHTML;
    }

    public String getAttributionText() {
        return mAttributionText;
    }

    public void setAttributionText(String attributionText) {
        mAttributionText = attributionText;
    }

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getEtag() {
        return mEtag;
    }

    public void setEtag(String etag) {
        mEtag = etag;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
