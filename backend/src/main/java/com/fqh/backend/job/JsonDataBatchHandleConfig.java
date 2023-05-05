package com.fqh.backend.job;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fqh.backend.entity.CodeInfo;
import com.fqh.backend.entity.CodeInfoJSON;
import com.fqh.backend.service.CodeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * json数据批处理数据处理配置
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@ConditionalOnProperty(value = "my.config.job1.enabled", havingValue = "true", matchIfMissing = true)
public class JsonDataBatchHandleConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;

    private final CodeInfoService codeInfoService;
    private final HashSet<Integer> questionNoSet = new HashSet<>(3000);

    @Autowired
    public JsonDataBatchHandleConfig(CodeInfoService codeInfoService) {
        this.codeInfoService = codeInfoService;
    }

    @Bean
    public JsonItemReader<CodeInfoJSON> jsonItemReader() {
        var objectMapper = new ObjectMapper();
        var jsonObjectReader = new JacksonJsonObjectReader<>(CodeInfoJSON.class);
        jsonObjectReader.setMapper(objectMapper);
        var resource = new ClassPathResource("data.json");
        return new JsonItemReaderBuilder<CodeInfoJSON>()
                .jsonObjectReader(jsonObjectReader)
                .resource(resource)
                .name("codeInfoJsonItemReader")
                .build();
    }

    @Bean
    public ItemWriter<CodeInfoJSON> databaseItemWriter() {
//        return items -> {
//            var data = items.stream()
//                    .filter(e -> !questionNoSet.contains(e.getQuestionNo()))
//                    .map(e -> {
//                        CodeInfo codeInfo = new CodeInfo();
//                        codeInfo.setQuestionNo(e.getQuestionNo());
//                        codeInfo.setQuestionName(e.getQuestionName());
//                        codeInfo.setQuestionUrl(e.getQuestionUrl());
//                        codeInfo.setCodeLang(e.getQuestionLang());
//                        codeInfo.setCode(e.getCode());
//                        codeInfo.setUploadTime(new Date());
//                        codeInfo.setUpdateTime(new Date());
//                        return codeInfo;
//                    }).collect(Collectors.toList());
//            boolean ok = codeInfoService.saveBatch(data);
//            if (ok)
//                log.info("save success");
//            else
//                log.info("save fail");
//        };
        return items -> {
            log.info("{}", questionNoSet);
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<CodeInfoJSON, CodeInfoJSON>chunk(10)
                .reader(jsonItemReader())
                .writer(databaseItemWriter())
                .build();
    }

    @Bean
    public Job job(Step step1) {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }


    @PostConstruct
    public void initQuestionNoSet() {
        var list = codeInfoService.list();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(e -> questionNoSet.add(e.getQuestionNo()));
        }
    }
}
