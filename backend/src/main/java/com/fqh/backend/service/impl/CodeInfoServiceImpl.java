package com.fqh.backend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.backend.dao.CodeInfoMapper;
import com.fqh.backend.entity.CodeInfo;
import com.fqh.backend.service.CodeInfoService;
import com.fqh.backend.vo.CodeInfoQueryVO;
import com.fqh.backend.vo.CodeInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author FQH
* @description 针对表【code_info(代码信息表)】的数据库操作Service实现
* @createDate 2023-05-02 19:49:02
*/
@Service
public class CodeInfoServiceImpl extends ServiceImpl<CodeInfoMapper, CodeInfo>
    implements CodeInfoService {

    private CodeInfoMapper codeInfoMapper;

    @Autowired
    public CodeInfoServiceImpl(CodeInfoMapper codeInfoMapper) {
        this.codeInfoMapper = codeInfoMapper;
    }

    @Override
    public List<CodeInfoVO> queryCodeInfo(CodeInfoQueryVO queryVO) {
//        var questionNo = queryVO.getQuestionNo();
//        var questionName = queryVO.getQuestionName();
        var keyword = queryVO.getKeyword();
        var wrapper = new LambdaQueryWrapper<CodeInfo>();
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.eq(CodeInfo::getQuestionNo, keyword);
            wrapper.like(CodeInfo::getQuestionName, keyword);
//            wrapper.select();  覆盖索引优化
        }
        var codeInfoList= codeInfoMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(codeInfoList)) {
            //TODO
            // throw new CustomerException...
        }
        var data = codeInfoList.stream().map(item -> {
            var target = new CodeInfoVO();
            BeanUtils.copyProperties(item, target);
            return target;
        }).collect(Collectors.toList());
        return data;
    }

}




