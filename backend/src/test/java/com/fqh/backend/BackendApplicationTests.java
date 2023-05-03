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
        codeInfo.setQuestionNo(5);
        codeInfo.setQuestionName("最长回文子串");
        codeInfo.setQuestionUrl("https://leetcode.cn/problems/longest-palindromic-substring/");
        codeInfo.setCodeLang(1);
        codeInfo.setCode("func longestPalindrome(s string) string {\n" +
                "    n, maxLen := len(s), 1\n" +
                "\tans := s\n" +
                "\tfor i := 1; i < n; i++ {\n" +
                "\t\tx := s[i]\n" +
                "\t\tl, r := i, i\n" +
                "\t\tfor l >= 0 && s[l] == x {\n" +
                "\t\t\tl--\n" +
                "\t\t}\n" +
                "\t\tfor r < n && s[r] == x {\n" +
                "\t\t\tr++\n" +
                "\t\t}\n" +
                "\t\tfor l >= 0 && r < n && s[l] == s[r] {\n" +
                "\t\t\tl--\n" +
                "\t\t\tr++\n" +
                "\t\t}\n" +
                "\t\tif r-l+1 > maxLen {\n" +
                "\t\t\tans = s[l+1 : r]\n" +
                "\t\t\tmaxLen = r - l + 1\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\treturn ans\n" +
                "}");
        codeInfo.setUploadTime(new Date());
        codeInfo.setUpdateTime(new Date());
        int row = codeInfoMapper.insert(codeInfo);
        if (row > 0) System.out.println("success");
    }

}
