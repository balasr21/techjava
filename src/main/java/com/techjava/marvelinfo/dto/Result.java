
package com.techjava.marvelinfo.dto;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    @JsonProperty("comics")
    private Comics mComics;
    @JsonProperty("description")
    private String mDescription;
    @JsonProperty("events")
    private Events mEvents;
    @JsonProperty("id")
    private int mId;
    @JsonProperty("modified")
    private String mModified;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("resourceURI")
    private String mResourceURI;
    @JsonProperty("series")
    private Series mSeries;
    @JsonProperty("stories")
    private Stories mStories;
    @JsonProperty("thumbnail")
    private Thumbnail mThumbnail;
    @JsonProperty("urls")
    private List<Url> mUrls;

    public Comics getComics() {
        return mComics;
    }

    public void setComics(Comics comics) {
        mComics = comics;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Events getEvents() {
        return mEvents;
    }

    public void setEvents(Events events) {
        mEvents = events;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

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

    public Series getSeries() {
        return mSeries;
    }

    public void setSeries(Series series) {
        mSeries = series;
    }

    public Stories getStories() {
        return mStories;
    }

    public void setStories(Stories stories) {
        mStories = stories;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
    }

    public List<Url> getUrls() {
        return mUrls;
    }

    public void setUrls(List<Url> urls) {
        mUrls = urls;
    }

}
