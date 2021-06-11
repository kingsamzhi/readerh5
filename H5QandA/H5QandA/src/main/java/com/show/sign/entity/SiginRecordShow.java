package com.show.sign.entity;

import com.show.sign.pojo.Signrecord;

import java.util.List;

public class SiginRecordShow {
    private List<Signrecord> signrecords;
    private int total=0;
    private String images;

    public List<Signrecord> getSignrecords() {
        return signrecords;
    }

    public void setSignrecords(List<Signrecord> signrecords) {
        this.signrecords = signrecords;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
