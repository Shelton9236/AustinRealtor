package com.ece656.house;

import com.ece6561.house.autoconfig.EnableHttpClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableHttpClient
@EnableAsync
@MapperScan(basePackages = {"com.ece656.house.biz.mapper"})
@ServletComponentScan
public class HouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}
