package com.show.sign.pojo;

import javax.persistence.Table;

@Table(name = "signrecord")
public class Signrecord {
    private Integer id;
    private Integer stuid;
    private String signdate;
    private String  groupid;
    private Integer flag;


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

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}
