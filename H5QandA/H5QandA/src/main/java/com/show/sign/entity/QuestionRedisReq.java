package com.show.sign.entity;

public class QuestionRedisReq {
    private Integer id;
    private Integer type;

    public QuestionRedisReq(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
