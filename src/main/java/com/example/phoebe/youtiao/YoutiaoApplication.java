package com.example.phoebe.youtiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@MapperScan("com.example.phoebe.youtiao.dao.api")
@ImportResource("classpath:spring/applicationContext.xml")
public class YoutiaoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YoutiaoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YoutiaoApplication.class);
    }
}
