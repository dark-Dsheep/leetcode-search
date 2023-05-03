package com.fqh.backend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fqh.backend.entity.CodeInfo;
import com.fqh.backend.vo.CodeInfoQueryVO;
import com.fqh.backend.vo.CodeInfoVO;

import java.util.List;

/**
* @author FQH
* @description 针对表【code_info(代码信息表)】的数据库操作Service
* @createDate 2023-05-02 19:49:02
*/
public interface CodeInfoService extends IService<CodeInfo> {
    /**
     * 查询代码信息
     * @param queryVO 查询条件
     * @return String
     */
    String queryCodeInfo(CodeInfoQueryVO queryVO);
}
