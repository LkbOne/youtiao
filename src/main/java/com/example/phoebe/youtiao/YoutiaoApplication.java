package com.example.phoebe.youtiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.phoebe.youtiao.dao.api")
public class YoutiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutiaoApplication.class, args);
    }

}
