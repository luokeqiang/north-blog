package com.luoke.iceblog.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author luoke
 * @date 2021/10/27 14:55
 */
@SpringBootApplication(scanBasePackages = {"com.luoke.iceblog.admin", "com.luoke.iceblog.common"})
@EnableOpenApi
@MapperScan(basePackages = {"com.luoke.iceblog.admin.mapper"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
