package com.show.sign.entity;

import com.show.sign.pojo.Mission;
import com.show.sign.pojo.Missiondetail;

public class MissionResult {
    private Missiondetail missiondetail;//题目
    private int nowtimes;//当前第几次答题
    private int num;//当前第几题
    private String date;//当前日期
    private String time;//当前时间
    private String code;
    private String msg;
    private Integer count;
    private Mission mission;

    public Missiondetail getMissiondetail() {
        return missiondetail;
    }

    public void setMissiondetail(Missiondetail missiondetail) {
        this.missiondetail = missiondetail;
    }

    public int getNowtimes() {
        return nowtimes;
    }

    public void setNowtimes(int nowtimes) {
        this.nowtimes = nowtimes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
