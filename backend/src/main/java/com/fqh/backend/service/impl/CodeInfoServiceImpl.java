package com.fqh.backend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.backend.dao.CodeInfoMapper;
import com.fqh.backend.entity.CodeInfo;
import com.fqh.backend.exception.BusinessException;
import com.fqh.backend.service.CodeInfoService;
import com.fqh.backend.vo.CodeInfoQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.fqh.backend.enums.ResultCode.NOT_FOUND;

/**
* @author FQH
* @description 针对表【code_info(代码信息表)】的数据库操作Service实现
* @createDate 2023-05-02 19:49:02
*/
@Service
public class CodeInfoServiceImpl extends ServiceImpl<CodeInfoMapper, CodeInfo>
    implements CodeInfoService {

    private final CodeInfoMapper codeInfoMapper;


    @Autowired
    public CodeInfoServiceImpl(CodeInfoMapper codeInfoMapper) {
        this.codeInfoMapper = codeInfoMapper;
    }

    //TODO cache result
    @Override
    public String queryCodeInfo(CodeInfoQueryVO queryVO) {
        var keyword = queryVO.getKeyword();
        var wrapper = new LambdaQueryWrapper<CodeInfo>();
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.eq(CodeInfo::getQuestionNo, keyword).or()
                    .eq(CodeInfo::getQuestionName, keyword);
//            wrapper.select();  覆盖索引优化
        }
        var codeInfoList= codeInfoMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(codeInfoList)) {
            throw new BusinessException(NOT_FOUND.getCode(), NOT_FOUND.getMessage());
        }
        return convertToMdStyle(codeInfoList);
    }

    /**
     * 转换为md格式
     * @param codedInfoList 代码信息数据
     * @return List<String>
     */
    private String convertToMdStyle(List<CodeInfo> codedInfoList) {
        var data = codedInfoList.get(0);
        final StringBuilder md = new StringBuilder();
        md.append("\uD83E\uDD16\n");
        md.append("### ").append(data.getQuestionNo()).append(".").append(data.getQuestionName()).append("\n");
        md.append("\uD83E\uDD23\uD83E\uDD23\uD83E\uDD23[题目链接](").append(data.getQuestionUrl()).append(")\n\n");
        md.append("**解题思路**\n").append("```text\n").append("正在编写中\uD83E\uDD23\n").append("```\n");
        for (var codeInfo : codedInfoList) {
            if (codeInfo.getCodeLang() == 0) {
                md.append("```java\n").append(codeInfo.getCode()).append("\n").append("```\n");
            } else {
                md.append("```go\n").append(codeInfo.getCode()).append("\n").append("```\n");
            }
        }
        return md.toString();
    }

}




