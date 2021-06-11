package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer schoolid;
	private Integer teacherid;
	private String name;
	private String 	dob;
	private Integer levels;
	private String 	bclass;
	private String 	bgrade;
	private String 	sex;
	private Integer flag;
	private Integer monthnum;
	private Integer weeknum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getBclass() {
		return bclass;
	}

	public void setBclass(String bclass) {
		this.bclass = bclass;
	}

	public String getBgrade() {
		return bgrade;
	}

	public void setBgrade(String bgrade) {
		this.bgrade = bgrade;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getMonthnum() {
		return monthnum;
	}

	public void setMonthnum(Integer monthnum) {
		this.monthnum = monthnum;
	}

	public Integer getWeeknum() {
		return weeknum;
	}

	public void setWeeknum(Integer weeknum) {
		this.weeknum = weeknum;
	}
}
