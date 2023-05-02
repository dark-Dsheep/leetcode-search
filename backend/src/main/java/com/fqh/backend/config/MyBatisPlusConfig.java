package com.fqh.backend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.fqh.backend.dao")
@Configuration
public class MyBatisPlusConfig {

}
