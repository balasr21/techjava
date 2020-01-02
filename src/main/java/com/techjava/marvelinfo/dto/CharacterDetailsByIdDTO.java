package com.techjava.marvelinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterDetailsByIdDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public CharacterDetailsByIdDTO(Integer id,String name, String description, String path,String extension) {
        this.id=id;
        this.thumbnail=new Thumbnail();
        this.name = name;
        this.description = description;
        this.thumbnail.setExtension(extension);
        this.thumbnail.setPath(path);

    }

    public CharacterDetailsByIdDTO(){

    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }


}
