package com.jxwgroup.oaserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * OA办公协同系统(毕业设计)
 * 启动类
 * @author 焦鑫威
 * @date 2023/11/28
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jxwgroup.oaserver.mapper")
public class OaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaServerApplication.class, args);
    }

}
