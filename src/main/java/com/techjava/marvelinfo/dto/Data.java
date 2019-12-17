
package com.techjava.marvelinfo.dto;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {

    @JsonProperty("count")
    private Long mCount;
    @JsonProperty("limit")
    private Long mLimit;
    @JsonProperty("offset")
    private Long mOffset;
    @JsonProperty("results")
    private List<Result> mResults;
    @JsonProperty("total")
    private Long mTotal;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public Long getOffset() {
        return mOffset;
    }

    public void setOffset(Long offset) {
        mOffset = offset;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
