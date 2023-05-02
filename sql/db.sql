CREATE DATABASE `my_project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE my_project;

create table `code_info` (
    id int(11) auto_increment comment '主键id',
    question_no int(11) not null comment '题号',
    question_url varchar(1024) comment '题目链接',
    code_lang tinyint(2) not null comment '代码语言 0-java 1-golang',
    code_info text(2048) not null comment '代码内容',
    upload_time date not null comment '上传时间',
    update_time date not null comment '更新时间',
    is_deleted tinyint(2) not null default 0 comment '逻辑删除 0-未删除 1-已删除',
    primary key (`id`)
) comment '代码信息表';