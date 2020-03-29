package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/28 22:09
 */
@SpringBootApplication
@EnableEurekaServer //开始eureka服务
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
