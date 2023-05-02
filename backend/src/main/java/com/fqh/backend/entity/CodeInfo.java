package com.fqh.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 代码信息表
 * @TableName code_info
 */
@TableName(value ="code_info")
@Data
public class CodeInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题号
     */
    @TableField(value = "question_no")
    private Integer questionNo;
    /**
     * 题目名称
     */
    @TableField(value = "question_name")
    private String questionName;

    /**
     * 题目链接
     */
    @TableField(value = "question_url")
    private String questionUrl;

    /**
     * 代码语言 0-java 1-golang
     */
    @TableField(value = "code_lang")
    private Integer codeLang;

    /**
     * 代码内容
     */
    @TableField(value = "code_info")
    private String codeInfo;

    /**
     * 上传时间
     */
    @TableField(value = "upload_time")
    private Date uploadTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除 0-未删除 1-已删除
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CodeInfo other = (CodeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestionNo() == null ? other.getQuestionNo() == null : this.getQuestionNo().equals(other.getQuestionNo()))
            && (this.getQuestionUrl() == null ? other.getQuestionUrl() == null : this.getQuestionUrl().equals(other.getQuestionUrl()))
            && (this.getCodeLang() == null ? other.getCodeLang() == null : this.getCodeLang().equals(other.getCodeLang()))
            && (this.getCodeInfo() == null ? other.getCodeInfo() == null : this.getCodeInfo().equals(other.getCodeInfo()))
            && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestionNo() == null) ? 0 : getQuestionNo().hashCode());
        result = prime * result + ((getQuestionUrl() == null) ? 0 : getQuestionUrl().hashCode());
        result = prime * result + ((getCodeLang() == null) ? 0 : getCodeLang().hashCode());
        result = prime * result + ((getCodeInfo() == null) ? 0 : getCodeInfo().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionNo=").append(questionNo);
        sb.append(", questionUrl=").append(questionUrl);
        sb.append(", codeLang=").append(codeLang);
        sb.append(", codeInfo=").append(codeInfo);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}