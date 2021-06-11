package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "accesslog")
public class Accesslog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;   //自增编号
    private String ipaddress;//ip地址
    private String timeer;//时间
    private String info;//时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getTimeer() {
        return timeer;
    }

    public void setTimeer(String timeer) {
        this.timeer = timeer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
