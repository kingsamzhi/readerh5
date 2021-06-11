package com.show.sign.pojo;

import javax.persistence.Table;

@Table(name = "orderstudent")
public class Orderstudent {
    private Integer schoolid;
    private String city;
    private String area;
    private String town;
    private String type;
    private String schoolname;
    private Integer teacherid;
    private String teachername;
    private Integer stuid;
    private String studentname;
    private Integer count;
    private Integer summark;
    private Double avgmark;
    private Double maxmark;
    private Double minmark;
    private Integer times;
    private Integer weeknum;
    private Integer monthnum;
    private Double totalscore;

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getSummark() {
        return summark;
    }

    public void setSummark(Integer summark) {
        this.summark = summark;
    }

    public Integer getWeeknum() {
        return weeknum;
    }

    public void setWeeknum(Integer weeknum) {
        this.weeknum = weeknum;
    }

    public Integer getMonthnum() {
        return monthnum;
    }

    public void setMonthnum(Integer monthnum) {
        this.monthnum = monthnum;
    }

    public Double getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Double totalscore) {
        this.totalscore = totalscore;
    }
}
