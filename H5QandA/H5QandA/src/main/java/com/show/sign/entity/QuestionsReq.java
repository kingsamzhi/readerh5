package com.show.sign.entity;

import com.show.sign.pojo.Options;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

public class QuestionsReq {

    private Integer id;
    private String groupid;
    private String title;
    private String type;
    private String answers;
    private String owner;
    private Integer flag;
    private Integer levels;
    private List<Options> options;
    private Integer showcount;
    private Integer succcount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public Integer getShowcount() {
        return showcount;
    }

    public void setShowcount(Integer showcount) {
        this.showcount = showcount;
    }

    public Integer getSucccount() {
        return succcount;
    }

    public void setSucccount(Integer succcount) {
        this.succcount = succcount;
    }
}
