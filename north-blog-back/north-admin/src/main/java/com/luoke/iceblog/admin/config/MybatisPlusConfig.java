package com.luoke.iceblog.admin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.luoke.iceblog.common.entity.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 *  mybatis plus 配置类
 * @author luoke
 * @date 2021/10/28 11:15
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://10.2.12.214:3306/ice_blog?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8", "luoke", "lkq413554")
                .globalConfig(builder -> {
                    builder.author("luoke") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\study\\Java\\project\\north-blog\\north-blog\\north-blog-back\\north-admin\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.luoke.iceblog.admin") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\study\\Java\\project\\north-blog\\north-blog\\north-blog-back\\north-admin\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addExclude("admin");
                    builder.entityBuilder()
                            .superClass(BaseEntity.class)
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .build();

                })
                .execute();
    }
}
