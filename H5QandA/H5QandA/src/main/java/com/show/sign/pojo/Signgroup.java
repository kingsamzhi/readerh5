package com.show.sign.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "signgroup")
public class Signgroup {
    @Id
    private String groupid;
    private String lastdate;
    private String photos;
    private String name;
    private String phone;
    private String address;
    private Integer flag;
    private String  shipmentcompany;
    private String shipmentnum;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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
