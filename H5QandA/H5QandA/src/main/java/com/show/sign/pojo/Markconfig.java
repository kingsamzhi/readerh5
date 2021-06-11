package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "markconfig")
public class Markconfig {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;   //自增编号
    private String	title;
    private String	photos;
    private Integer	markstart;
    private Integer	markend;
    private String	levels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Integer getMarkstart() {
        return markstart;
    }

    public void setMarkstart(Integer markstart) {
        this.markstart = markstart;
    }

    public Integer getMarkend() {
        return markend;
    }

    public void setMarkend(Integer markend) {
        this.markend = markend;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }
}
