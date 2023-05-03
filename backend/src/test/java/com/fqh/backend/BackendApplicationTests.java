package com.fqh.backend;

import com.fqh.backend.dao.CodeInfoMapper;
import com.fqh.backend.entity.CodeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private CodeInfoMapper codeInfoMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void dataLoad() {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setQuestionNo(49);
        codeInfo.setQuestionName("字母异位词分组");
        codeInfo.setQuestionUrl("https://leetcode.cn/problems/group-anagrams/");
        codeInfo.setCodeLang(1);
        codeInfo.setCode("func groupAnagrams(strs []string) (ans [][]string) {\n" +
                "    mp := make(map[string][]string)\n" +
                "\tfor _, str := range strs {\n" +
                "\t\tcs := []byte(str)\n" +
                "\t\tsort.Slice(cs, func(i, j int) bool {\n" +
                "\t\t\treturn cs[i] < cs[j]\n" +
                "\t\t})\n" +
                "\t\ts := string(cs)\n" +
                "\t\tmp[s] = append(mp[s], str)\n" +
                "\t}\n" +
                "\tfor _, v := range mp {\n" +
                "\t\tans = append(ans, v)\n" +
                "\t}\n" +
                "\treturn ans\n" +
                "}");
        codeInfo.setUploadTime(new Date());
        codeInfo.setUpdateTime(new Date());
        int row = codeInfoMapper.insert(codeInfo);
        if (row > 0) System.out.println("success");
    }

}
