package com.luoke.northblog.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author luoke
 * @date 2021/10/27 14:55
 */
@SpringBootApplication
@EnableOpenApi
@MapperScan(basePackages = {"com.luoke.northblog.admin.mapper"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
