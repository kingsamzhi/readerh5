package com.show.sign.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "division")
public class Division {
	@Id
    private Integer id;

    private String code;

    private String pcode;

    private String name;

    private Integer level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		 this.code = code == null ? null : code.trim();
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		 this.pcode = pcode == null ? null : pcode.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

    
}