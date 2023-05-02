package com.fqh.backend.controller;

import com.fqh.backend.common.CommonResult;
import com.fqh.backend.service.CodeInfoService;
import com.fqh.backend.vo.CodeInfoQueryVO;
import com.fqh.backend.vo.CodeInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fqh.backend.common.CommonResult.ok;

@Slf4j
@RestController
@RequestMapping("/codeinfo")
public class CodeInfoController {

    @Autowired
    private CodeInfoService codeInfoService;

    @PostMapping("/queryCodeInfo")
    public CommonResult<List<CodeInfoVO>> queryCodeInfo(@RequestBody CodeInfoQueryVO queryVO) {
        log.info("{}", queryVO);
//        var data = codeInfoService.queryCodeInfo(queryVO);
        var data = List.of(new CodeInfoVO(), new CodeInfoVO());
        return ok(data);
    }
}
