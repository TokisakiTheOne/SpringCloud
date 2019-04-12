package com.yyh.eureka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author YanYuHang
 * @create 2019-04-06-9:44
 */
@SpringCloudApplication
@EnableEurekaServer
public class Applocation {
    public static void main(String[] args) {
        SpringApplication.run(Applocation.class, args);
    }
}
