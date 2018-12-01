package com.gd.itcarrier.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gd.itcarrier.api.dao.CityDao;
import com.gd.itcarrier.api.domain.City;

/**
 * 服务应用启动类
 * @author yuzg
 *
 */
@SpringBootApplication
@MapperScan("com.gd.itcarrier.api.dao")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}
