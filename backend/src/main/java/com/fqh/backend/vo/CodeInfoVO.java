package com.fqh.backend.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@EqualsAndHashCode
@ToString
@Data
public class CodeInfoVO {

    /**
     * 题号
     */
    private Integer questionNo;
    /**
     * 题目名称
     */
    private String questionName;

    /**
     * 题目链接
     */
    private String questionUrl;

    /**
     * 代码语言 0-java 1-golang
     */
    private Integer codeLang;

    /**
     * 代码内容
     */
    private String codeInfo;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
