package com.fqh.backend.entity;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CodeInfoJSON {

    private Integer questionNo;
    private String questionName;
    private String questionUrl;
    private Integer questionLang;
    private String code;

    public CodeInfoJSON() {}

    public CodeInfoJSON(Integer questionNo, String questionName, String questionUrl, Integer questionLang, String code) {
        this.questionNo = questionNo;
        this.questionName = questionName;
        this.questionUrl = questionUrl;
        this.questionLang = questionLang;
        this.code = code;
    }
}
