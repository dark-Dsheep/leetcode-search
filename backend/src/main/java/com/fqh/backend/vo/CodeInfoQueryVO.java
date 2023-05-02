package com.fqh.backend.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class CodeInfoQueryVO {
    /**
     * 题号
     */
    private Integer questionNo;
    /**
     * 题目名称
     */
    private String questionName;

    /**
     * 关键字
     */
    private String keyword;
}
