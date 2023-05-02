package com.fqh.backend.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.backend.dao.CodeInfoMapper;
import com.fqh.backend.entity.CodeInfo;
import com.fqh.backend.service.CodeInfoService;
import org.springframework.stereotype.Service;

/**
* @author FQH
* @description 针对表【code_info(代码信息表)】的数据库操作Service实现
* @createDate 2023-05-02 19:49:02
*/
@Service
public class CodeInfoServiceImpl extends ServiceImpl<CodeInfoMapper, CodeInfo>
    implements CodeInfoService {


}




