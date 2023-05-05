package com.fqh.backend.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

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
    @Length(min = 1, max = 30)
    @NotBlank
    private String keyword;
}
