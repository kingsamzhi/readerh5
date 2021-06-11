package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mission")
public class Mission {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer	stuid;
	private String	starttime;
	private String	startdate;
	private Integer	levels;
	private Integer	totalnum;
	private Integer	finishnum;
	private Integer	correctnum;
	private Integer	mark;
	private Integer signflag;
	private String endtime;
	private Integer times;



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

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getFinishnum() {
		return finishnum;
	}

	public void setFinishnum(Integer finishnum) {
		this.finishnum = finishnum;
	}

	public Integer getCorrectnum() {
		return correctnum;
	}

	public void setCorrectnum(Integer correctnum) {
		this.correctnum = correctnum;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getSignflag() {
		return signflag;
	}

	public void setSignflag(Integer signflag) {
		this.signflag = signflag;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
}
