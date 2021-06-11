package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "stumedaldetail")
public class Stumedaldetail {
    @Id
    private Integer id;   //自增编号
    private Integer	stuid;
    private String	gettime;
    private String	groupid;
    private Integer	medalid;
    private String	name;
    private String	 phone;
    private String	address;
    private Integer	flag;
    private String	shipmentcompany;
    private String	shipmentnum;


    private String	title;
    private String	photos;
    private String	starttime;
    private String	endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public Integer getMedalid() {
        return medalid;
    }

    public void setMedalid(Integer medalid) {
        this.medalid = medalid;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getShipmentcompany() {
        return shipmentcompany;
    }

    public void setShipmentcompany(String shipmentcompany) {
        this.shipmentcompany = shipmentcompany;
    }

    public String getShipmentnum() {
        return shipmentnum;
    }

    public void setShipmentnum(String shipmentnum) {
        this.shipmentnum = shipmentnum;
    }
}
