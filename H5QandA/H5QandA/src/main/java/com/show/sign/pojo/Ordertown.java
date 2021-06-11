package com.show.sign.pojo;

import javax.persistence.Table;

@Table(name = "ordertown")
public class Ordertown {
    private String city;
    private String area;
    private String town;
    private Integer count;
    private Double avgmark;
    private Double maxmark;
    private Double minmark;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAvgmark() {
        return avgmark;
    }

    public void setAvgmark(Double avgmark) {
        this.avgmark = avgmark;
    }

    public Double getMaxmark() {
        return maxmark;
    }

    public void setMaxmark(Double maxmark) {
        this.maxmark = maxmark;
    }

    public Double getMinmark() {
        return minmark;
    }

    public void setMinmark(Double minmark) {
        this.minmark = minmark;
    }
}
