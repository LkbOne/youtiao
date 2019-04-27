package com.example.phoebe.youtiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.example.phoebe.youtiao.dao.api")
@ImportResource("classpath:spring/applicationContext.xml")
public class YoutiaoApplication {

    public static void main(String[] args) {

        SpringApplication.run(YoutiaoApplication.class, args);
    }

}
