package com.show.sign.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "stuerrorbook")
public class Stuerrorbook {
	@Id
	private Integer id;
	private Integer	stuid;
	private String title;
	private String	options;
	private String	type;
	private String	answer;
	private Integer	level;
	private String	stuanswer;
	private String	createtime;
	private Integer	createnum;
	private Integer questionid;
	private Integer sturesult;
	private String answertime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStuanswer() {
		return stuanswer;
	}

	public void setStuanswer(String stuanswer) {
		this.stuanswer = stuanswer;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getCreatenum() {
		return createnum;
	}

	public void setCreatenum(Integer createnum) {
		this.createnum = createnum;
	}

	public Integer getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

	public Integer getSturesult() {
		return sturesult;
	}

	public void setSturesult(Integer sturesult) {
		this.sturesult = sturesult;
	}

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
}
