package com.show.sign.entity;

import com.show.sign.utils.ExcelHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelModel {

    @ExcelHeader(value = "编号")
    private BigDecimal id;
    @ExcelHeader(value = "题目")
    private String title;
    @ExcelHeader(value = "选项 A")
    private String optionA;
    @ExcelHeader(value = "选项 B")
    private String optionB;
    @ExcelHeader(value = "选项 C")
    private String optionC;
    @ExcelHeader(value = "选项 D")
    private String optionD;
    @ExcelHeader(value = "选项 E")
    private String optionE;
    @ExcelHeader(value = "选项 F")
    private String optionF;
    @ExcelHeader(value = "选项 G")
    private String optionG;
    @ExcelHeader(value = "选项 H")
    private String optionH;
    @ExcelHeader(value = "选项 I")
    private String optionI;
    @ExcelHeader(value = "选项 J")
    private String optionJ;
    @ExcelHeader(value = "答案")
    private String answer;
    @ExcelHeader(value = "类型")
    private String type;
    @ExcelHeader(value = "出题人")
    private String owner;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    public String getOptionF() {
        return optionF;
    }

    public void setOptionF(String optionF) {
        this.optionF = optionF;
    }

    public String getOptionG() {
        return optionG;
    }

    public void setOptionG(String optionG) {
        this.optionG = optionG;
    }

    public String getOptionH() {
        return optionH;
    }

    public void setOptionH(String optionH) {
        this.optionH = optionH;
    }

    public String getOptionI() {
        return optionI;
    }

    public void setOptionI(String optionI) {
        this.optionI = optionI;
    }

    public String getOptionJ() {
        return optionJ;
    }

    public void setOptionJ(String optionJ) {
        this.optionJ = optionJ;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
