package com.yyd.yyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class YydApplication {

    public static void main(String[] args) {
        SpringApplication.run(YydApplication.class, args);
    }

}
