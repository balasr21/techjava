package com.techjava.marvelinfo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="characterdetails",schema = "dbo")
public class CharacterDetails implements Serializable {

    private static final long serialVersionUID = 8466580944866736089L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public long id;

    @Column(name="characterid")
    public Integer characterId;

    @Column(name="name")
    public String name;

    @Column(name="description")
    public String description;


    @Column(name="thumbnailpath")
    public String thumbnailPath;

    @Column(name="extension")
    public String extension;

    @Column(name="searchtimestamp")
    public Date searchTimestamp;



    /**
     * getId
     *
     * @return Id
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get Description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get ThumbnailPath
     * @return thumbnailPath
     */
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    /**
     * set Thumbnail Path
     * @param thumbnailPath
     */
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    /**
     * get Extension
     * @return exetnsion
     */
    public String getExtension() {
        return extension;
    }

    /**
     * setExtension
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * getSearchTimestamp
     * @return
     */
    public Date getSearchTimestamp() {
        return searchTimestamp;
    }

    /**
     * setSearchTimestamp
     * @param searchTimestamp
     */
    public void setSearchTimestamp(Date searchTimestamp) {
        this.searchTimestamp = searchTimestamp;
    }

}
