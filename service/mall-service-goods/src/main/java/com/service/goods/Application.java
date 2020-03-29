package com.service.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 14:10
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "{com.service.goods.dao}")  //开启通用mapper扫描
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
