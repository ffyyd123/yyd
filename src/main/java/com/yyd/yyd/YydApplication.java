package com.yyd.yyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author yyd
 */
@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
@EnableOpenApi
@MapperScan("com.yyd.yyd.dao.mapper")
//开启aspect 默认未false使用jdk的动态代理  true表示使用cglib动态代理
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class YydApplication {

    public static void main(String[] args) {
        SpringApplication.run(YydApplication.class, args);
    }

}
